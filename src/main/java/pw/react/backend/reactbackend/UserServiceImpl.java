package pw.react.backend.reactbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addNewUser(NewUserRequest newUserRequest){
        if(userRepository.getUserByLogin(newUserRequest.getLogin()).isPresent())
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

//    private boolean userAlreadyExists(String login) {
//        if(userRepository.getUserByLogin(login).isPresent())
//            return true;
//        return false;
//    }

//    private boolean doesUserExists(long id) {
//        if(userRepository.getUser(id).isPresent()) return true;
//        return false;
//    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
