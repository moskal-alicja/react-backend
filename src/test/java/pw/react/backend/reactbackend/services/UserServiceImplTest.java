package pw.react.backend.reactbackend.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pw.react.backend.reactbackend.*;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Before
    public void setUp() throws Exception {
        service = spy(new UserServiceImpl(repository));
    }

    @Test
    public void givenExistingLogin_whenAddUser_thenThrowLoginAlreadyExistsException() {
        NewUserRequest newUser = new NewUserRequest();
        User user = new User();
        String login = "login";
        newUser.setLogin(login);
        user.setLogin(login);
        when(repository.getUserByLogin(anyString())).thenReturn(Optional.of(user));
        try{
            service.addNewUser(newUser);
            fail("Should throw LoginAlreadyExistsException");
        }
        catch(LoginAlreadyExistsException ex) {
            assertThat(ex.getMessage(), is(equalTo("Login login already exists.")));
        }
        verify(repository, times(0)).addNewUser(any(NewUserRequest.class));

    }

    @Test
    public void givenUniqueLogin_whenAddUser_thenExecuteAddNewUser() {
        NewUserRequest newUser = new NewUserRequest();
        long id = 1;
        String login = "uniqueLogin1";
        newUser.setLogin(login);
        newUser.setId(id);
        when(repository.getUserByLogin(anyString())).thenReturn(Optional.empty());

        service.addNewUser(newUser);

        verify(repository, times(1)).addNewUser(eq(newUser));
    }

    @Test
    public void givenNotExistingUser_whenDeleteUser_thenThrowUserNotFoundException() {
        User user = new User();
        long id = 1;
        user.setId(id);
        when(repository.isUserPresent(anyLong())).thenReturn(false);
        try {
            service.deleteUser(id);
            fail("Should throw UserNotFoundException");
        }
        catch(UserNotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo("User 1 not found.")));
        }
        verify(repository, times(0)).deleteUser(anyLong());
    }

    @Test
    public void givenExistingUser_whenDeleteUser_thenExecuteDeleteUser() {
        long id = 1;
        when(repository.isUserPresent(anyLong())).thenReturn(true);

        service.deleteUser(id);

        verify(repository, times(1)).deleteUser(eq(id));
    }


    @Test
    public void givenNotExistingUser_whenUpdateUser_thenThrowUserNotFoundException() {
        NewUserRequest user = new NewUserRequest();
        long id = 1;
        user.setId(id);
        when(repository.updateUser(any(NewUserRequest.class), anyLong())).thenReturn(Optional.empty());

        try {
            service.updateUser(user, id);
            fail("Should throw UserNotFoundException");
        }
        catch (UserNotFoundException ex) {
            assertThat(ex.getMessage(), is(equalTo("User 1 not found.")));
        }
        verify(repository, times(1)).updateUser(any(NewUserRequest.class), anyLong());

    }

    @Test
    public void givenExistingUser_whenUpdateUser_thenExecuteSaveMethod() {
        NewUserRequest newUser = new NewUserRequest();
        User user = new User();
        long id = 1;
        user.setId(id);

        when(repository.updateUser(any(NewUserRequest.class), anyLong())).thenReturn(Optional.of(user));

        service.updateUser(newUser, id);

        verify(repository, times(1)).updateUser(eq(newUser), eq(id));
    }


}
