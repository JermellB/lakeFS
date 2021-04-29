package api

import (
	"context"
	"crypto/subtle"
	"errors"
	"fmt"
	"net/http"
	"strings"

	"github.com/getkin/kin-openapi/openapi3"
	"github.com/getkin/kin-openapi/routers"
	"github.com/getkin/kin-openapi/routers/legacy"
	"github.com/treeverse/lakefs/pkg/auth"
	"github.com/treeverse/lakefs/pkg/auth/model"
	"github.com/treeverse/lakefs/pkg/logging"
	"gopkg.in/dgrijalva/jwt-go.v3"
)

var (
	ErrUnexpectedSigningMethod = errors.New("unexpected signing method")
	ErrAuthenticationFailed    = errors.New("error authenticating request")
)

// extractSecurityRequirements using Swagger returns an array of security requirements set for the request.
func extractSecurityRequirements(router routers.Router, r *http.Request) (openapi3.SecurityRequirements, error) {
	// Find route
	route, _, err := router.FindRoute(r)
	if err != nil {
		return nil, err
	}
	if route.Operation.Security == nil {
		return route.Swagger.Security, nil
	}
	return *route.Operation.Security, nil
}

func AuthMiddleware(logger logging.Logger, swagger *openapi3.Swagger, authService auth.Service) func(next http.Handler) http.Handler {
	router, err := legacy.NewRouter(swagger)
	if err != nil {
		panic(err)
	}
	return func(next http.Handler) http.Handler {
		return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
			securityRequirements, err := extractSecurityRequirements(router, r)
			if err != nil {
				writeError(w, http.StatusBadRequest, err)
				return
			}
			user, err := checkSecurityRequirements(r, securityRequirements, logger, authService)
			if err != nil {
				writeError(w, http.StatusUnauthorized, err)
				return
			}
			if user != nil {
				r = r.WithContext(context.WithValue(r.Context(), UserContextKey, user))
			}
			next.ServeHTTP(w, r)
		})
	}
}

// checkSecurityRequirements goes over the security requirements and check the authentication. returns the user information and error if the security check was required.
// it will return nil user and error in case of no security checks to match.
func checkSecurityRequirements(r *http.Request, securityRequirements openapi3.SecurityRequirements, logger logging.Logger, authService auth.Service) (*model.User, error) {
	ctx := r.Context()
	var user *model.User
	var err error
	for _, securityRequirement := range securityRequirements {
		for provider := range securityRequirement {
			switch provider {
			case "jwt_token":
				// validate jwt token from header
				authHeaderValue := r.Header.Get("Authorization")
				if authHeaderValue == "" {
					continue
				}
				parts := strings.Fields(authHeaderValue)
				if len(parts) != 2 || !strings.EqualFold(parts[0], "Bearer") {
					continue
				}
				token := parts[1]
				user, err = userByToken(ctx, logger, authService, token)
			case "basic_auth":
				// validate using basic auth
				accessKey, secretKey, ok := r.BasicAuth()
				if !ok {
					continue
				}
				user, err = userByAuth(ctx, logger, authService, accessKey, secretKey)
			case "cookie_auth":
				// validate jwt token from cookie
				jwtCookie, _ := r.Cookie(JWTCookieName)
				if jwtCookie == nil {
					continue
				}
				user, err = userByToken(ctx, logger, authService, jwtCookie.Value)
			default:
				// unknown security requirement to check
				logger.WithField("provider", provider).Error("Authentication middleware unknown security requirement provider")
				return nil, ErrAuthenticationFailed
			}
			if err != nil {
				return nil, err
			}
			if user != nil {
				return user, nil
			}
		}
	}
	return nil, nil
}

func userByToken(ctx context.Context, logger logging.Logger, authService auth.Service, tokenString string) (*model.User, error) {
	claims := &jwt.StandardClaims{}
	token, err := jwt.ParseWithClaims(tokenString, claims, func(token *jwt.Token) (interface{}, error) {
		if _, ok := token.Method.(*jwt.SigningMethodHMAC); !ok {
			return nil, fmt.Errorf("%w: %s", ErrUnexpectedSigningMethod, token.Header["alg"])
		}
		return authService.SecretStore().SharedSecret(), nil
	})
	if err != nil {
		return nil, ErrAuthenticationFailed
	}
	claims, ok := token.Claims.(*jwt.StandardClaims)
	if !ok || !token.Valid {
		return nil, ErrAuthenticationFailed
	}
	cred, err := authService.GetCredentials(ctx, claims.Subject)
	if err != nil {
		logger.WithField("subject", claims.Subject).Info("could not find credentials for token")
		return nil, ErrAuthenticationFailed
	}
	userData, err := authService.GetUserByID(ctx, cred.UserID)
	if err != nil {
		logger.WithFields(logging.Fields{
			"user_id": cred.UserID,
			"subject": claims.Subject,
		}).Debug("could not find user id by credentials")
		return nil, ErrAuthenticationFailed
	}
	return userData, nil
}

func userByAuth(ctx context.Context, logger logging.Logger, authService auth.Service, accessKey string, secretKey string) (*model.User, error) {
	cred, err := authService.GetCredentials(ctx, accessKey)
	if err != nil {
		logger.WithError(err).Error("failed getting credentials for key")
		return nil, ErrAuthenticationFailed
	}
	if subtle.ConstantTimeCompare([]byte(secretKey), []byte(cred.SecretAccessKey)) != 1 {
		logger.Debug("access key secret does not match")
		return nil, ErrAuthenticationFailed
	}
	user, err := authService.GetUserByID(ctx, cred.UserID)
	if err != nil {
		logger.WithFields(logging.Fields{"user_id": cred.UserID}).Debug("could not find user id by credentials")
		return nil, ErrAuthenticationFailed
	}
	return user, nil
}
