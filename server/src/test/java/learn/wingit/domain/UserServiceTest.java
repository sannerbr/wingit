package learn.wingit.domain;

import learn.wingit.data.UserJdbcTemplateRepository;
import learn.wingit.models.Role;
import learn.wingit.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserServiceTest {

    @Autowired
    UserService service;

    @MockBean
    UserJdbcTemplateRepository repository;

    @Test
    void shouldAddUser() {
        Result<User> result = service.add(makeUser());
        assertTrue(result.isSuccess());
        assertEquals("chicken", result.getPayload().getUsername());
    }

    @Test
    void shouldFindById() {
        User user = makeUser();
        user.setUserId(1);
        when(service.findById(1)).thenReturn(user);
        User result = service.findById(1);
        assertNotNull(result);
        assertEquals(1, result.getUserId());
    }

//    @Test
//    void shouldFindByUsername() {
//        User user = makeUser();
//        user.setUserId(1);
//        when(service.findByUsername("chicken")).thenReturn(user);
//        User result = service.findByUsername("chicken");
//        assertNotNull(result);
//        assertEquals("chicken", result.getUsername());
//    }

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
        when(repository.findAll()).thenReturn(listOfUsers());
        User user = makeUser();
        user.setUsername("rhino");
        Result<User> result = service.add(user);
        assertFalse(result.isSuccess());
        assertEquals("This username is already taken", result.getMessages().get(0));
    }

    @Test
    void shouldUpdateUser() {
        User user = makeUser();
        user.setUserId(2);
        when(repository.updateUser(user)).thenReturn(true);
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

    @Test
    void shouldNotDeleteUser() {
        when(repository.deleteUser(800)).thenReturn(false);
        boolean result = service.delete(800);
        assertFalse(result);
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

    private List<User> listOfUsers() {
        User user = makeUser();
        user.setUserId(1);
        User user2 = makeUser();
        user2.setUsername("rhino");
        user2.setUserId(2);
        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user2);
        return list;
    }

}