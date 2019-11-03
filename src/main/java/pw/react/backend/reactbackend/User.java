package pw.react.backend.reactbackend;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    private String name;
    private String lastName;
    private String birthDate;
    private boolean active;
    private String login;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setActive(boolean active) {
        this.active = active;
    }



    public User() {
    }


    public User(String name, String lastName, String birthDate, boolean active, String login) {
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.active = active;
        this.login = login;
    }


    public String getLogin() {
        return login;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public boolean isActive() {
        return active;
    }


}
