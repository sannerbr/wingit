package learn.wingit.domain;

import learn.wingit.data.UserRepository;
import learn.wingit.models.Role;
import learn.wingit.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserServiceTest {

    @Autowired
    UserService service;

    @MockBean
    UserRepository repository;

    @Test
    void shouldAddUser() {
        Result<User> result = service.add(makeUser());
        assertTrue(result.isSuccess());
        assertEquals("chicken_password", result.getPayload().getPassword());
    }

    @Test
    void shouldNotAddUserWithNoUsername() {
        User user = makeUser();
        user.setUsername(null);
        Result<User> result = service.add(user);
        assertFalse(result.isSuccess());
        assertEquals( "Username is required.", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddUserWhereIdIsSet() {
        User user = makeUser();
        user.setUserId(2);
        Result<User> result = service.add(user);
        assertFalse(result.isSuccess());
        assertEquals("User ID cannot be set for user", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddNullUser() {
        User user = null;
        Result<User> result = service.add(user);
        assertFalse(result.isSuccess());
        assertEquals("User cannot be null", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddDuplicateUser() {
        User user = makeUser();
        when(repository.addUser(user)).thenReturn(user);
        Result<User> result = service.add(user);
        User user2 = makeUser();
        user2.setPassword("hcic");

        Result<User> result2 = service.add(user2);
        assertFalse(result2.isSuccess());
        assertEquals("This username is already taken", result2.getMessages().get(0));

        //TODO Add custom validation here
    }

    @Test
    void shouldUpdateUser() {
        User user = makeUser();
        user.setUserId(2);
        Result<User> result = service.update(user);
        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotUpdateUserWhereThereIsNoUserId() {
        User user = makeUser();
        Result<User> result = service.update(user);
        assertFalse(result.isSuccess());
        assertEquals("User ID is required", result.getMessages().get(0));
    }

    @Test
    void shouldNotUpdateUserForInvalidUserId() {
        User user = makeUser();
        user.setUserId(9000);
        Result<User> result = service.update(user);
        assertFalse(result.isSuccess());
        assertEquals("User id: 9000 is not found!", result.getMessages().get(0));
    }

    @Test
    void shouldDeleteUser() {
        when(repository.deleteUser(2)).thenReturn(true);
        boolean result = service.delete(2);
        assertTrue(result);
    }

    private User makeUser() {
        User user = new User();
        user.setUsername("chicken");
        user.setPassword("chicken_password");
        user.setEmail("chicken@gmail.com");
        user.setPhone("903-900-9111");
        user.setAddress("333 Ave.");
        user.setCompany("Chicken Inc.");
        user.setOrders(null);
        user.setRole(Role.USER);
        return user;
    }

}