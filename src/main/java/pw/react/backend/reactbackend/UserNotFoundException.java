package pw.react.backend.reactbackend;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User Not Found")
public class UserNotFoundException extends RuntimeException {

    UserNotFoundException(Long id) {
        super("User " + id + " not found.");
    }
}
