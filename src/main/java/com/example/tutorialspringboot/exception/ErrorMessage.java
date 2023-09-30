package com.example.tutorialspringboot.exception;

import java.util.Date;

// Estou criando minha propria estrutura de mensagem de erro em vez de usar a do Spring como a do NullPointerException
public class ErrorMessage {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;

    // Em OO diferenca entre declarar uma classe ou seu construtor é o 'class'
    public ErrorMessage(int statusCode, Date timestamp, String message, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
