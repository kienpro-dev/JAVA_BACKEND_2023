package hit.club.shopmanagement.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleErrorNotFound(NotFoundException e) {
        return new ErrorResponse(e.getMessage(), e.getStatus());
    }

    @ExceptionHandler(InternalServerException.class)
    public ErrorResponse handleErrorInternalServer(InternalServerException e){
        return new ErrorResponse(e.getMessage(), e.getStatus());
    }
}
