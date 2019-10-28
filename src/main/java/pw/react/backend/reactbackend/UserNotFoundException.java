package pw.react.backend.reactbackend;

public class UserNotFoundException extends RuntimeException {

    UserNotFoundException(Long id) {
        super("Could not find user " + id);
    }
}
