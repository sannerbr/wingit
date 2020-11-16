package learn.wingit.data;

import learn.wingit.models.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll();

    User findById(int userId);

    User findByUsername(String username);

    User addUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(int userId);
}
