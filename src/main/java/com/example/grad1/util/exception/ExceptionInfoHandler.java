package com.example.grad1.util.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;


@RestControllerAdvice(annotations = RestController.class)
public class ExceptionInfoHandler {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ErrorInfo notFound(HttpServletRequest req, Exception e) {
        return new ErrorInfo(req.getRequestURL(), e);
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ErrorInfo conflict(HttpServletRequest req, DataIntegrityViolationException e) {
        return new ErrorInfo(req.getRequestURL(), e);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(VoteTimeViolationException.class)
    @ResponseBody
    public ErrorInfo votingTimeViolation(HttpServletRequest req, VoteTimeViolationException e) {
        return new ErrorInfo(req.getRequestURL(), e);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorInfo illegalArgument(HttpServletRequest req, IllegalArgumentException e) {
        return new ErrorInfo(req.getRequestURL(), e);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalRequestDataException.class)
    @ResponseBody
    public ErrorInfo votingDuplicate(HttpServletRequest req, IllegalRequestDataException e) {
        return new ErrorInfo(req.getRequestURL(), e);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorInfo throwException(HttpServletRequest req, MethodArgumentTypeMismatchException e) {

        return new ErrorInfo(req.getRequestURL(), e);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundActivationException.class)
    public ErrorInfo notFoundActivationCode(HttpServletRequest req, NullPointerException e){
        return new ErrorInfo(req.getRequestURL(), e);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullPointerException.class)
    public ErrorInfo nullEntity(HttpServletRequest req, NullPointerException e) {
        return new ErrorInfo(req.getRequestURL(), e);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorInfo handleError(HttpServletRequest req, Exception e) {
        return new ErrorInfo(req.getRequestURL(), e);
    }

}