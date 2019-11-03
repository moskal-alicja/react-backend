package pw.react.backend.reactbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.awt.SunToolkit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path="/users")
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path="")
    public ResponseEntity<User> AddNewUser(@RequestBody @Valid NewUserRequest newUserRequest) {
        return ResponseEntity.ok().body(userService.addNewUser(newUserRequest));
    }

    @DeleteMapping(path="")
    public String DeleteUser(@RequestParam long id, HttpServletResponse response) {
        userService.deleteUser(id);
        return String.format("user %s deleted", id);
    }

    @PutMapping(path="/{id}")
    public User UpdateUser(@RequestBody NewUserRequest newUserRequest, @PathVariable long id) {
        return userService.updateUser(newUserRequest, id);
    }

    @GetMapping(path="")
    public ResponseEntity<List<User>> GetAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<User> GetUser(@PathVariable long id) throws UserNotFoundException {
        return ResponseEntity.ok().body(userService.getUser(id));
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    String userNotFoundHandler(UserNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(LoginAlreadyExistsException.class)
    String loginAlreadyExistsHandler(LoginAlreadyExistsException ex) {
        return ex.getMessage();
    }

}
