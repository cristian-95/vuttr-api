package api.vuttr.exception.handler;

import api.vuttr.exception.MissingFieldsFromInputException;
import api.vuttr.exception.ResourceNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        HttpStatus status;

        System.out.println(ex.toString());


        if (ex instanceof ConstraintViolationException
                || ex instanceof MethodArgumentNotValidException
                || ex instanceof IllegalArgumentException) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), new Date(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, status);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), new Date(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingFieldsFromInputException.class)
    public final ResponseEntity<ExceptionResponse> handleMissingFieldsFromInputException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), new Date(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
