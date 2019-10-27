package pw.react.backend.reactbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

interface UserCrudRepository extends CrudRepository<User, Integer> {}

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserCrudRepository crudRepository;

    public Integer  getUsersCountByLogin(String login){
        return jdbcTemplate.queryForObject("select count(*) from Users where login = (?)", Integer.class, login);

    }

    public void addNewUser(NewUserRequest newUserRequest){
        User user = new User(newUserRequest.getName(),newUserRequest.getLastName(), newUserRequest.getBirthDate(), newUserRequest.isActive(), newUserRequest.getLogin());
        crudRepository.save(user);
    }

}

