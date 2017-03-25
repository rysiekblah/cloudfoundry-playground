package com.github.rysiekblah.hashmap.exception;

/**
 * Created by Tomasz_Kozlowski on 2/26/2017.
 */
public class InstanceException extends RuntimeException {
    public InstanceException(String message) {
        super(message);
    }

    public InstanceException(String message, Throwable cause) {
        super(message, cause);
    }
}
