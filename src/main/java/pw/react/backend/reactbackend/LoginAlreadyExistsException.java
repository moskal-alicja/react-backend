package pw.react.backend.reactbackend;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Login already exists")
public class LoginAlreadyExistsException extends RuntimeException {

    LoginAlreadyExistsException(String login) {
        super( String.format("Login %s already exists.", login));
    }
}
