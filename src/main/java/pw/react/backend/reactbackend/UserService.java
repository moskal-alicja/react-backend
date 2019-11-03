package pw.react.backend.reactbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {
    User updateUser(NewUserRequest newUserRequest, long id);
    User addNewUser(NewUserRequest newUserRequest);
    boolean deleteUser(long id);
    User getUser(long id);
    List<User> getAllUsers();
}


