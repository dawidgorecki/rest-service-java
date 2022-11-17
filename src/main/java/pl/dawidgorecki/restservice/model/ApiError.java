package pl.dawidgorecki.restservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiError {

    private int httpCode;
    private List<String> errors;

    public ApiError(int status, List<String> errors) {
        this.httpCode = status;
        this.errors = errors;
    }

    public ApiError(int status, String error) {
        this.httpCode = status;
        errors = List.of(error);
    }
}