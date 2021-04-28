/*
 * lakeFS API
 * lakeFS HTTP API
 *
 * The version of the OpenAPI document: 0.1.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package io.treeverse.lakefs.clients;

import io.treeverse.lakefs.ApiCallback;
import io.treeverse.lakefs.ApiClient;
import io.treeverse.lakefs.ApiException;
import io.treeverse.lakefs.ApiResponse;
import io.treeverse.lakefs.Configuration;
import io.treeverse.lakefs.Pair;
import io.treeverse.lakefs.ProgressRequestBody;
import io.treeverse.lakefs.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import io.treeverse.lakefs.clients.api.model.Error;
import io.treeverse.lakefs.clients.api.model.StagingLocation;
import io.treeverse.lakefs.clients.api.model.StagingMetadata;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StagingApi {
    private ApiClient localVarApiClient;

    public StagingApi() {
        this(Configuration.getDefaultApiClient());
    }

    public StagingApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for getPhysicalAddress
     * @param repository  (required)
     * @param branch  (required)
     * @param path  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> physical address for staging area </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Resource Not Found </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Internal Server Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getPhysicalAddressCall(String repository, String branch, String path, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/repositories/{repository}/branches/{branch}/staging/backing"
            .replaceAll("\\{" + "repository" + "\\}", localVarApiClient.escapeString(repository.toString()))
            .replaceAll("\\{" + "branch" + "\\}", localVarApiClient.escapeString(branch.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (path != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("path", path));
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "basic_auth", "cookie_auth", "jwt_token" };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getPhysicalAddressValidateBeforeCall(String repository, String branch, String path, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'repository' is set
        if (repository == null) {
            throw new ApiException("Missing the required parameter 'repository' when calling getPhysicalAddress(Async)");
        }
        
        // verify the required parameter 'branch' is set
        if (branch == null) {
            throw new ApiException("Missing the required parameter 'branch' when calling getPhysicalAddress(Async)");
        }
        
        // verify the required parameter 'path' is set
        if (path == null) {
            throw new ApiException("Missing the required parameter 'path' when calling getPhysicalAddress(Async)");
        }
        

        okhttp3.Call localVarCall = getPhysicalAddressCall(repository, branch, path, _callback);
        return localVarCall;

    }

    /**
     * get a physical address and a return token to write object to underlying storage
     * 
     * @param repository  (required)
     * @param branch  (required)
     * @param path  (required)
     * @return StagingLocation
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> physical address for staging area </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Resource Not Found </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Internal Server Error </td><td>  -  </td></tr>
     </table>
     */
    public StagingLocation getPhysicalAddress(String repository, String branch, String path) throws ApiException {
        ApiResponse<StagingLocation> localVarResp = getPhysicalAddressWithHttpInfo(repository, branch, path);
        return localVarResp.getData();
    }

    /**
     * get a physical address and a return token to write object to underlying storage
     * 
     * @param repository  (required)
     * @param branch  (required)
     * @param path  (required)
     * @return ApiResponse&lt;StagingLocation&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> physical address for staging area </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Resource Not Found </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Internal Server Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<StagingLocation> getPhysicalAddressWithHttpInfo(String repository, String branch, String path) throws ApiException {
        okhttp3.Call localVarCall = getPhysicalAddressValidateBeforeCall(repository, branch, path, null);
        Type localVarReturnType = new TypeToken<StagingLocation>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * get a physical address and a return token to write object to underlying storage (asynchronously)
     * 
     * @param repository  (required)
     * @param branch  (required)
     * @param path  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> physical address for staging area </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Resource Not Found </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Internal Server Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getPhysicalAddressAsync(String repository, String branch, String path, final ApiCallback<StagingLocation> _callback) throws ApiException {

        okhttp3.Call localVarCall = getPhysicalAddressValidateBeforeCall(repository, branch, path, _callback);
        Type localVarReturnType = new TypeToken<StagingLocation>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for linkPhysicalAddress
     * @param repository  (required)
     * @param branch  (required)
     * @param path  (required)
     * @param stagingMetadata  (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> successfully linked </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Validation Error </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Internal Server Error </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> conflict with a commit, try here </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Internal Server Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call linkPhysicalAddressCall(String repository, String branch, String path, StagingMetadata stagingMetadata, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = stagingMetadata;

        // create path and map variables
        String localVarPath = "/repositories/{repository}/branches/{branch}/staging/backing"
            .replaceAll("\\{" + "repository" + "\\}", localVarApiClient.escapeString(repository.toString()))
            .replaceAll("\\{" + "branch" + "\\}", localVarApiClient.escapeString(branch.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (path != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("path", path));
        }

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "basic_auth", "cookie_auth", "jwt_token" };
        return localVarApiClient.buildCall(localVarPath, "PUT", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call linkPhysicalAddressValidateBeforeCall(String repository, String branch, String path, StagingMetadata stagingMetadata, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'repository' is set
        if (repository == null) {
            throw new ApiException("Missing the required parameter 'repository' when calling linkPhysicalAddress(Async)");
        }
        
        // verify the required parameter 'branch' is set
        if (branch == null) {
            throw new ApiException("Missing the required parameter 'branch' when calling linkPhysicalAddress(Async)");
        }
        
        // verify the required parameter 'path' is set
        if (path == null) {
            throw new ApiException("Missing the required parameter 'path' when calling linkPhysicalAddress(Async)");
        }
        
        // verify the required parameter 'stagingMetadata' is set
        if (stagingMetadata == null) {
            throw new ApiException("Missing the required parameter 'stagingMetadata' when calling linkPhysicalAddress(Async)");
        }
        

        okhttp3.Call localVarCall = linkPhysicalAddressCall(repository, branch, path, stagingMetadata, _callback);
        return localVarCall;

    }

    /**
     * associate staging on this physical address with a path
     * If the supplied token matches the current staging token, associate the object as the physical address with the supplied path.  Otherwise, if staging has been committed and the token has expired, return a conflict and hint where to place the object to try again.  Caller should copy the object to the new physical address and PUT again with the new staging token.  (No need to back off, this is due to losing the race against a concurrent commit operation.) 
     * @param repository  (required)
     * @param branch  (required)
     * @param path  (required)
     * @param stagingMetadata  (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> successfully linked </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Validation Error </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Internal Server Error </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> conflict with a commit, try here </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Internal Server Error </td><td>  -  </td></tr>
     </table>
     */
    public void linkPhysicalAddress(String repository, String branch, String path, StagingMetadata stagingMetadata) throws ApiException {
        linkPhysicalAddressWithHttpInfo(repository, branch, path, stagingMetadata);
    }

    /**
     * associate staging on this physical address with a path
     * If the supplied token matches the current staging token, associate the object as the physical address with the supplied path.  Otherwise, if staging has been committed and the token has expired, return a conflict and hint where to place the object to try again.  Caller should copy the object to the new physical address and PUT again with the new staging token.  (No need to back off, this is due to losing the race against a concurrent commit operation.) 
     * @param repository  (required)
     * @param branch  (required)
     * @param path  (required)
     * @param stagingMetadata  (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> successfully linked </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Validation Error </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Internal Server Error </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> conflict with a commit, try here </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Internal Server Error </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> linkPhysicalAddressWithHttpInfo(String repository, String branch, String path, StagingMetadata stagingMetadata) throws ApiException {
        okhttp3.Call localVarCall = linkPhysicalAddressValidateBeforeCall(repository, branch, path, stagingMetadata, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * associate staging on this physical address with a path (asynchronously)
     * If the supplied token matches the current staging token, associate the object as the physical address with the supplied path.  Otherwise, if staging has been committed and the token has expired, return a conflict and hint where to place the object to try again.  Caller should copy the object to the new physical address and PUT again with the new staging token.  (No need to back off, this is due to losing the race against a concurrent commit operation.) 
     * @param repository  (required)
     * @param branch  (required)
     * @param path  (required)
     * @param stagingMetadata  (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> successfully linked </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Validation Error </td><td>  -  </td></tr>
        <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Internal Server Error </td><td>  -  </td></tr>
        <tr><td> 409 </td><td> conflict with a commit, try here </td><td>  -  </td></tr>
        <tr><td> 0 </td><td> Internal Server Error </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call linkPhysicalAddressAsync(String repository, String branch, String path, StagingMetadata stagingMetadata, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = linkPhysicalAddressValidateBeforeCall(repository, branch, path, stagingMetadata, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
}
