package com.demo.kalaha.exception;

public class KalahaIllegalMoveException extends RuntimeException {

    public KalahaIllegalMoveException(String message) {

        super("KalahaIllegalMoveException : " +message);
    }
}
