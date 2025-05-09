package com.brainventory_mgmt.auth.exceptions;

public class GlobalAdminAlreadyExistsException extends RuntimeException {
    public GlobalAdminAlreadyExistsException(String message) {
        super(message);
    }
}