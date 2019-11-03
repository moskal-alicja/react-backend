package pw.react.backend.reactbackend;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class NewUserRequest {

    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private String birthDate;
    @NotNull
    private boolean active;
    @NotNull
    private String login;

    public void setLogin(String login) {
        this.login = login;
    }

    private Long testId;


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

    public String getLogin() {
        return login;
    }

    public NewUserRequest() {
    }

    public NewUserRequest(String name, String lastName, String birthDate, boolean active, String login) {
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.active = active;
        this.login = login;
    }

    public void setId(long l) {
        this.testId = l;
    }
}
