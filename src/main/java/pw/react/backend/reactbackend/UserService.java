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

@Service
class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addNewUser(NewUserRequest newUserRequest){
        if(userAlreadyExists(newUserRequest.getLogin()))
            throw new LoginAlreadyExistsException(newUserRequest.getLogin());
        return userRepository.addNewUser(newUserRequest);
    }

    public boolean deleteUser(long id){
        if(userRepository.isUserPresent(id)) {
            userRepository.deleteUser(id);
            return true;
        }
        else throw new UserNotFoundException(id);
    }

    public User getUser(long id) {
        Optional<User> user = userRepository.getUser((id));
        if(user.isPresent())
            return  user.get();
        else
            throw new UserNotFoundException(id);
    }

    @Override
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

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
