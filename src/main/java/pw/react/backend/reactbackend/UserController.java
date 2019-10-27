package pw.react.backend.reactbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

}
