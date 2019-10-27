package pw.react.backend.reactbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean addNewUser(NewUserRequest newUserRequest){
        if(userAlreadyExists(newUserRequest.getLogin()))
            return false;
        userRepository.addNewUser(newUserRequest);
        return true;
    }

    private boolean userAlreadyExists(String login) {
        if(userRepository.getUsersCountByLogin(login) > 0)
            return true;
        return false;
    }



}
