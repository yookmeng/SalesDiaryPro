package com.SpringMVC.model;

public class ApiReturn {
    private Boolean status;
    private String error_message;
    private String json;

    public ApiReturn() {
    }
 
    public ApiReturn(Boolean status, String error_message, String json) {
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

    public String getjson() {
        return json;
    }
    public void setjson(String json) {
        this.json = json;
    }
}