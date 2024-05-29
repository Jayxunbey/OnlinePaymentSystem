package uz.pdp.online.onlinepayment.common.exceptions.handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class CategoryExceptionHandler {

    // Exception Handling Methods
    @ExceptionHandler({CategoryNotFoundException.class})
    public ResponseEntity<Map<String, String>> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler({CategoryCreateException.class, CategoryUpdateException.class, CategoryDeleteException.class})
    public ResponseEntity<Map<String, String>> handleCategoryCreateException(RuntimeException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Map<String, String>>> handleValidationException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        Map<String, Map<String, String>> errors = new HashMap<>();
        Map<String, String> fieldErrorDtos = new HashMap<>();

        fieldErrors.forEach(fieldError -> fieldErrorDtos.put(fieldError.getField(), fieldError.getDefaultMessage()));
        errors.put("errors", fieldErrorDtos);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }



    // Custom Exception Classes
    public static class CategoryNotFoundException extends RuntimeException {
        public CategoryNotFoundException(String message) {
            super(message);
        }
    }

    public static class CategoryCreateException extends RuntimeException {
        public CategoryCreateException(String message) {
            super(message);
        }
    }

    public static class CategoryUpdateException extends RuntimeException {
        public CategoryUpdateException(String message) {
            super(message);
        }
    }

    public static class CategoryDeleteException extends RuntimeException {
        public CategoryDeleteException(String message) {
            super(message);
        }
    }
}
