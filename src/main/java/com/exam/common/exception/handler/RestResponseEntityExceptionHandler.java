package com.exam.common.exception.handler;

import com.exam.common.exception.InvalidEmailFormatException;
import com.exam.otp.exception.InvalidOTPException;
import com.exam.otp.exception.OTPNotFoundException;
import com.exam.user.exception.UnregisteredEmailException;
import com.exam.user.exception.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class, UnregisteredEmailException.class, OTPNotFoundException.class})
    public ResponseEntity<ErrorMessageEntity> handlePortalException(Exception e, WebRequest request){
        ErrorMessageEntity entity = new ErrorMessageEntity(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entity);
    }

    @ExceptionHandler({MessagingException.class})
    public ResponseEntity<ErrorMessageEntity> handleNotificationException(Exception e, WebRequest request){
        final String message = "Some error occurred while sending the email";
        ErrorMessageEntity entity = new ErrorMessageEntity(HttpStatus.INTERNAL_SERVER_ERROR, message);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(entity);
    }

    @ExceptionHandler({InvalidOTPException.class})
    public ResponseEntity<ErrorMessageEntity> handleInvalidOTPException(Exception e, WebRequest request){
        ErrorMessageEntity entity = new ErrorMessageEntity(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(entity);
    }

    @ExceptionHandler({InvalidEmailFormatException.class})
    public ResponseEntity<ErrorMessageEntity> handleInvalidEmailFormatException(Exception e, WebRequest request){
        ErrorMessageEntity entity = new ErrorMessageEntity(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(entity);
    }

//    @ExceptionHandler({MethodArgumentNotValidException.class})
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> response = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            final String fieldName = ((FieldError) error).getField();
            final String message = error.getDefaultMessage();
            response.put(fieldName, message);
        });

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
