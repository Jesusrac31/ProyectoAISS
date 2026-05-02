package aiss.videominer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "A comment with the provided ID already exists")
public class CommentAlreadyExistsException extends Exception{
}
