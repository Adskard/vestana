package cz.cvut.fel.nss.vestana.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidStateException extends BaseException {

    public InvalidStateException(String message) {
        super(message);
    }

    public InvalidStateException() {}

    public InvalidStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidStateException(Throwable cause) {
        super(cause);
    }
}
