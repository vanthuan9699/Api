package com.example.api.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExampleContact {

@SerializedName("status")
@Expose
private Integer status;
@SerializedName("code")
@Expose
private Integer code;
@SerializedName("result")
@Expose
private List<ResultContact> result = null;
@SerializedName("message")
@Expose
private String message;

public Integer getStatus() {
return status;
}

public void setStatus(Integer status) {
this.status = status;
}

public Integer getCode() {
return code;
}

public void setCode(Integer code) {
this.code = code;
}

public List<ResultContact> getResult() {
return result;
}

public void setResult(List<ResultContact> result) {
this.result = result;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

}