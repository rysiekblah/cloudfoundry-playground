package com.github.rysiekblah.hashmap.model;

/**
 * Created by Tomasz_Kozlowski on 3/19/2017.
 */
public class ErrorMessage {
    private String message;

    public ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
