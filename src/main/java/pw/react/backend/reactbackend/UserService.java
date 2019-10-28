package pw.react.backend.reactbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public boolean deleteUser(long id){
        if(userRepository.isUserPresent(id)) {
            userRepository.deleteUser(id);
            return true;
        }
        return false;

    }

    public User getUser(long id) {
        Optional<User> user = userRepository.getUser((id));
        if(user.isPresent())
            return  user.get();
        else
            throw new UserNotFoundException(id);
    }

    public User updateUser(NewUserRequest newUserRequest, long id) {
        Optional<User> user = userRepository.updateUser(newUserRequest, id);
        if(user.isPresent())
            return user.get();
        else throw new UserNotFoundException(id);
    }

    private boolean userAlreadyExists(String login) {
        if(userRepository.getUsersCountByLogin(login) > 0)
            return true;
        return false;
    }

    private boolean doesUserExists(long id) {
        if(userRepository.getUser(id).isPresent()) return true;
        return false;
    }

}
