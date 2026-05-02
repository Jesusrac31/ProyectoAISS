package aiss.videominer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "A video with the provided ID already exists")
public class VideoAlreadyExistsException extends Exception{
}
