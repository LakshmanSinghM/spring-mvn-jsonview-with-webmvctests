package com.lakshman.springmvc_json.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String s) {
        super(s);
    }
}