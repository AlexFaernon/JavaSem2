package com.pavlenko.MySpringBoot2Dbase.model;

public class Response<T> {
    public boolean success;
    public String message;   // опционально: текстовое сообщение
    public T data;

    public Response(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
