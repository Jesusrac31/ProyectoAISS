package aiss.peertubeminer.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handles cases where the external service (Videominer) is completely unreachable
    // (e.g., 503 Service Unavailable (server is offline))
    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<Map<String, Object>> handleOfflineServer(ResourceAccessException e) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("type", "about:blank");
        response.put("title", "Service Unavailable");
        response.put("detail", "Videominer is offline or unreachable");
        response.put("status", 503);
        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }

    // Handles 4xx Client Errors (e.g., 400 Bad Request, 404 Not Found)
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleClientError(HttpClientErrorException e) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        return new ResponseEntity<>(e.getResponseBodyAsString(), headers, e.getStatusCode());
    }

    // Handles 5xx Server Errors (e.g., 500 Internal Server Error)
    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<String> handleServerError(HttpServerErrorException e) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        return new ResponseEntity<>(e.getResponseBodyAsString(), headers, e.getStatusCode());
    }

}
