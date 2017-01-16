package com.SpringMVC.model;

import org.json.simple.JSONObject;

public class ApiReturn {
    private Boolean status;
    private String error_message;
    private JSONObject json;

    public ApiReturn() {
    }
 
    public ApiReturn(Boolean status, String error_message, JSONObject json) {
        this.status = status;        
        this.error_message = error_message;
        this.json = json;
    }
 
    public Boolean getstatus() {
        return status;
    }
    public void setstatus(Boolean status) {
        this.status = status;
    }

    public String geterror_message() {
        return error_message;
    }
    public void seterror_message(String error_message) {
        this.error_message = error_message;
    }

    public JSONObject getjson() {
        return json;
    }
    public void setjson(JSONObject json) {
        this.json = json;
    }
}