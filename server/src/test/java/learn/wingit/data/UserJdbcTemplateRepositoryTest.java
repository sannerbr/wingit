package learn.wingit.data;
import learn.wingit.models.Role;
import learn.wingit.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserJdbcTemplateRepositoryTest {

    @Autowired
    UserJdbcTemplateRepository jdbcTemplateRepository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setUp() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<User> users = jdbcTemplateRepository.findAll();
        assertNotNull(users);
        assertTrue(users.size() > 0);
    }

    @Test
    void shouldFindById() {
         User user  =  jdbcTemplateRepository.findById(2);
         assertNotNull(user);
         assertEquals("222-222-2222", user.getPhone());
    }

    @Test
    void shouldNotFindById() {
        User user = jdbcTemplateRepository.findById(80000);
        assertNull(user);
    }

    @Test
    void shouldFindUserByUsername() {
        User user  =  jdbcTemplateRepository.findByUsername("admin");
        assertNotNull(user);
        assertEquals("admin-pw-hash", user.getPassword());
    }

    @Test
    void shouldAddUser() {
        User user = makeUser();
        User result = jdbcTemplateRepository.addUser(user);
        assertNotNull(result);
        assertEquals("chicken", user.getUsername());
    }

    @Test
    void shouldUpdateUser() {
        User user = makeUser();
        user.setUserId(2);
        boolean result =  jdbcTemplateRepository.updateUser(user);
        assertTrue(result);
    }

    @Test
    void shouldNotUpdateUser() {
        User user = makeUser();
        user.setUserId(5000);
        boolean result =  jdbcTemplateRepository.updateUser(user);
        assertFalse(result);
    }

    @Test
    void shouldDeleteUser() {
      boolean result = jdbcTemplateRepository.deleteUser(2);
      assertTrue(result);
    }

    @Test
    void shouldNotDeleteUser() {
        boolean result = jdbcTemplateRepository.deleteUser(8000);
        assertFalse(result);
    }



    private User makeUser() {
        User user = new User();
        user.setUsername("chicken");
        user.setPassword("chickenpassword");
        user.setEmail("chicken@gmail.com");
        user.setPhone("717-903-3232");
        user.setAddress("112 Chicken Rd.");
        user.setCompany(null);
        user.setOrders(null);
        user.setRole(Role.USER);
        return user;
    }

}