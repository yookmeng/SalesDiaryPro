package com.SpringMVC.oneapi.model.common;

/**
 * specific error case indicated by the OneAPI server as a Service Exception 
 */ 
public class ServiceException implements java.io.Serializable { 
    private static final long serialVersionUID = -5777229181193910571L; 
 
    /**
     * the distinctive error message identifier 
     */ 
    private String messageId = null; 
    /**
     * the textual representation of the error 
     */ 
    private String text = null; 
    /**
     * any instance specific error variables 
     */ 
    private String[] variables = null; 
 
    /**
     * return the distinctive error message identifier 
     */ 
    public String getMessageId() { 
        return messageId; 
    } 
 
    /**
     * return the textual representation of the error 
     */ 
    public String getText() { 
        return text; 
    } 
 
    /**
     * return any instance specific error variables 
     */ 
    public String[] getVariables() { 
        return variables; 
    } 
 
    /**
     * set the distinctive error message identifier. This is called internally to set the contents according to the JSON response. 
     */ 
    public void setMessageId(String messageId) { 
        this.messageId = messageId; 
    } 
 
    /**
     * set the textual representation of the error. This is called internally to set the contents according to the JSON response. 
     */ 
    public void setText(String text) { 
        this.text = text; 
    } 
 
    /**
     * set any instance specific error variables. This is called internally to set the contents according to the JSON response. 
     */ 
    public void setVariables(String[] variables) { 
        this.variables = variables; 
    } 
 
    /**
     * default constructor 
     */ 
    public ServiceException() { 
    } 
 
    /**
     * utility constructor to create a ServiceException object with all fields set 
     */ 
    public ServiceException(String messageId, String text, String[] variables) { 
        this.messageId = messageId; 
        this.text = text; 
        this.variables = variables; 
    } 
 
    /**
     * generate a textual representation of the ServiceException instance 
     */ 
    public String toString() { 
        StringBuilder stringBuilder = new StringBuilder(); 
        stringBuilder.append("messageId = "); 
        stringBuilder.append(messageId); 
        stringBuilder.append(", text = "); 
        stringBuilder.append(text); 
        stringBuilder.append(", variables = {"); 
        if (variables != null) { 
            for (int i = 0; i < variables.length; i++) { 
                stringBuilder.append("["); 
                stringBuilder.append(i); 
                stringBuilder.append("] = "); 
                stringBuilder.append(variables[i]); 
            } 
        } 
        stringBuilder.append("}"); 
        return stringBuilder.toString(); 
    } 
 
 
}