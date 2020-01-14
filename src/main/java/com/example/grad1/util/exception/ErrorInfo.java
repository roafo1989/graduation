package com.example.grad1.util.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDateTime;

public class ErrorInfo {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    public final LocalDateTime time;
    public final String url;
    public final String cause;
    public final String detail;



    public ErrorInfo(CharSequence url, Throwable ex){
        this.time = LocalDateTime.now();
        this.url = url.toString();
        this.cause = ex.getClass().getSimpleName();
        this.detail = ex.getLocalizedMessage();
    }
}