package pw.react.backend.reactbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

interface UserCrudRepository extends CrudRepository<User, Long> {
}

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserCrudRepository crudRepository;

    public Integer  getUsersCountByLogin(String login){
        return jdbcTemplate.queryForObject("select count(*) from Users where login = (?)", Integer.class, login);

    }

    public User addNewUser(NewUserRequest newUserRequest){
        User user = new User(newUserRequest.getName(),newUserRequest.getLastName(), newUserRequest.getBirthDate(), newUserRequest.isActive(), newUserRequest.getLogin());
        return crudRepository.save(user);
    }

    public boolean isUserPresent(long id) {
        return crudRepository.findById(id).isPresent();
    }

    public void deleteUser(long id) {
            crudRepository.deleteById(id);
    }

    public Optional<User> getUser(long id) {
        return crudRepository.findById(id);
    }

    public Optional<User> updateUser(NewUserRequest newUserRequest, long id) {
        return crudRepository.findById(id).map(user -> {
            user.setName(newUserRequest.getName());
            user.setLastName(newUserRequest.getLastName());
            user.setBirthDate(newUserRequest.getBirthDate());
            user.setActive(newUserRequest.isActive());
            crudRepository.save(user);
            return user;
        });

    }

    public List<User> findAll() {
        List<User> users = new ArrayList<User>();
        for (User user : crudRepository.findAll()) {
            users.add(user);
        }
       return users;
    }

}

