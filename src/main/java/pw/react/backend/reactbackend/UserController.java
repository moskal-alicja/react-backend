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
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(path="/new-user")
    public String AddNewUser(@RequestBody NewUserRequest newUserRequest) {
        if (userService.addNewUser(newUserRequest))
            return "new user created";
        return "login already exists";
    }

    @PostMapping(path="/delete-user")
    public String DeleteUser(@RequestParam long id, HttpServletResponse response) {
        if (userService.deleteUser(id))
            return "user deleted";
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return "user does not exists";
    }

    @PutMapping(path="/update-user/{id}")
    public User UpdateUser(@RequestBody NewUserRequest newUserRequest, @PathVariable long id) {
        return userService.updateUser(newUserRequest, id);
    }

    @GetMapping(path="/get-user")
    public User GetUser(@RequestParam long id) throws InvalidParameterException {
        return userService.getUser(id);
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userNotFoundHandler(UserNotFoundException ex) {
        return ex.getMessage();
    }


}
