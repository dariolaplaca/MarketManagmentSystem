package com.dlp.mms.DTOs;

public class ResponseStringDTO {
    private String message;

    public ResponseStringDTO(String message){
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
