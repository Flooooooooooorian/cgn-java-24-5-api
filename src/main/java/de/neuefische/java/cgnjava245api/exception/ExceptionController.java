package de.neuefische.java.cgnjava245api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/exception")
public class ExceptionController {

    @GetMapping
    public String getException() {
        throw new NoSuchElementException("Student with id 1 not found!");
//        return "success";
    }

    @PostMapping
    public String addException() {
        throw new IllegalArgumentException("Exception");
//        return "success";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleIllegalArgumentException(IllegalArgumentException exception) {
        return new ErrorMessage(exception.getMessage()+ " local");
    }
}
