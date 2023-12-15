package com.mortgage.plan.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private int status;

    private String message;

    private List<String> errors;

    public ErrorResponse(HttpStatus status, String message) {

        this.status = status.value();
        this.message = message;
    }

    public ErrorResponse(HttpStatus status, List<String> errors) {

        this.status = status.value();
        this.message = status.getReasonPhrase();
        this.errors = errors;
    }
}
