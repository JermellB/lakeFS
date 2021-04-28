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


package io.treeverse.lakefs.clients.api.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import org.threeten.bp.OffsetDateTime;

/**
 * HookRun
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2021-04-28T14:59:02.133Z[GMT]")
public class HookRun {
  public static final String SERIALIZED_NAME_HOOK_RUN_ID = "hook_run_id";
  @SerializedName(SERIALIZED_NAME_HOOK_RUN_ID)
  private String hookRunId;

  public static final String SERIALIZED_NAME_ACTION = "action";
  @SerializedName(SERIALIZED_NAME_ACTION)
  private String action;

  public static final String SERIALIZED_NAME_HOOK_ID = "hook_id";
  @SerializedName(SERIALIZED_NAME_HOOK_ID)
  private String hookId;

  public static final String SERIALIZED_NAME_START_TIME = "start_time";
  @SerializedName(SERIALIZED_NAME_START_TIME)
  private OffsetDateTime startTime;

  public static final String SERIALIZED_NAME_END_TIME = "end_time";
  @SerializedName(SERIALIZED_NAME_END_TIME)
  private OffsetDateTime endTime;

  /**
   * Gets or Sets status
   */
  @JsonAdapter(StatusEnum.Adapter.class)
  public enum StatusEnum {
    FAILED("failed"),
    
    COMPLETED("completed");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static StatusEnum fromValue(String value) {
      for (StatusEnum b : StatusEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<StatusEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final StatusEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public StatusEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return StatusEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_STATUS = "status";
  @SerializedName(SERIALIZED_NAME_STATUS)
  private StatusEnum status;


  public HookRun hookRunId(String hookRunId) {
    
    this.hookRunId = hookRunId;
    return this;
  }

   /**
   * Get hookRunId
   * @return hookRunId
  **/
  @ApiModelProperty(required = true, value = "")

  public String getHookRunId() {
    return hookRunId;
  }


  public void setHookRunId(String hookRunId) {
    this.hookRunId = hookRunId;
  }


  public HookRun action(String action) {
    
    this.action = action;
    return this;
  }

   /**
   * Get action
   * @return action
  **/
  @ApiModelProperty(required = true, value = "")

  public String getAction() {
    return action;
  }


  public void setAction(String action) {
    this.action = action;
  }


  public HookRun hookId(String hookId) {
    
    this.hookId = hookId;
    return this;
  }

   /**
   * Get hookId
   * @return hookId
  **/
  @ApiModelProperty(required = true, value = "")

  public String getHookId() {
    return hookId;
  }


  public void setHookId(String hookId) {
    this.hookId = hookId;
  }


  public HookRun startTime(OffsetDateTime startTime) {
    
    this.startTime = startTime;
    return this;
  }

   /**
   * Get startTime
   * @return startTime
  **/
  @ApiModelProperty(required = true, value = "")

  public OffsetDateTime getStartTime() {
    return startTime;
  }


  public void setStartTime(OffsetDateTime startTime) {
    this.startTime = startTime;
  }


  public HookRun endTime(OffsetDateTime endTime) {
    
    this.endTime = endTime;
    return this;
  }

   /**
   * Get endTime
   * @return endTime
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public OffsetDateTime getEndTime() {
    return endTime;
  }


  public void setEndTime(OffsetDateTime endTime) {
    this.endTime = endTime;
  }


  public HookRun status(StatusEnum status) {
    
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(required = true, value = "")

  public StatusEnum getStatus() {
    return status;
  }


  public void setStatus(StatusEnum status) {
    this.status = status;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HookRun hookRun = (HookRun) o;
    return Objects.equals(this.hookRunId, hookRun.hookRunId) &&
        Objects.equals(this.action, hookRun.action) &&
        Objects.equals(this.hookId, hookRun.hookId) &&
        Objects.equals(this.startTime, hookRun.startTime) &&
        Objects.equals(this.endTime, hookRun.endTime) &&
        Objects.equals(this.status, hookRun.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hookRunId, action, hookId, startTime, endTime, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HookRun {\n");
    sb.append("    hookRunId: ").append(toIndentedString(hookRunId)).append("\n");
    sb.append("    action: ").append(toIndentedString(action)).append("\n");
    sb.append("    hookId: ").append(toIndentedString(hookId)).append("\n");
    sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
    sb.append("    endTime: ").append(toIndentedString(endTime)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

