package com.demo.kalaha.exception;

public class KalahaException extends RuntimeException {
    public KalahaException(String message) {

        super("KalahaException : " +message);
    }
}
