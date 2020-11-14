package learn.wingit.data;

import learn.wingit.data.mappers.OrderMapper;
import learn.wingit.data.mappers.PlaneMapper;
import learn.wingit.data.mappers.UserMapper;
import learn.wingit.models.Order;
import learn.wingit.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserJdbcTemplateRepository implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        final String sql = "select user_id, role_id, username, password_hash, `email`, `phone`, `address`, `company` from `user`;";

        List<User> users = jdbcTemplate.query(sql, new UserMapper());

        for (User user : users) {
            addOrders(user);
        }

        return users;
    }

    @Override
    public User findById(int userId) {
        final String sql = "select user_id, role_id, username, password_hash, `email`, `phone`, `address`, `company` from `user` where user_id = ?;";

        User user = jdbcTemplate.query(sql, new UserMapper(), userId).stream().findFirst().orElse(null);

        if(user != null) {
            addOrders(user);
        }
        return user;
    }

    @Override
    public User findByUsername(String username) {
        final String sql = "select user_id, role_id, username, password_hash, `email`, `phone`, `address`, `company` from `user` where username = ?;";

        User user = jdbcTemplate.query(sql, new UserMapper(), username).stream().findFirst().orElse(null);

        if(user != null) {
            addOrders(user);
        }
        return user;
    }

    @Override
    public boolean updateUser(User user) {

        final String sql = "update `user` set " +
                "role_id = ?, " +
                "username = ?, " +
                "password_hash = ?, " +
                "`email` = ?, " +
                "`phone` = ?, " +
                "`address` = ?, " +
                "`company` = ? where user_id = ?;";

        return jdbcTemplate.update(sql, user.getRole().getRoleId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getPhone(), user.getAddress(),
                 user.getCompany(), user.getUserId()) > 0;
    }

    @Override
    public boolean deleteUser(int userId) {
        User user = findById(userId);
        for(Order order : user.getOrders()) {
            jdbcTemplate.update("delete from order_plane where order_id = ?;", order.getOrderId());
        }
        jdbcTemplate.update("delete from `order` where user_id = ?;", userId);
        return jdbcTemplate.update("delete from `user` where user_id = ?;", userId) > 0;
    }

    private void addOrders(User user) {
        final String sql =
                "select o.order_id, o.user_id, o.order_date, o.total_cost from `order` o " +
                        "inner join `user` u on u.user_id = o.user_id where o.user_id = ?;";

        var orders = jdbcTemplate.query(sql, new OrderMapper(), user.getUserId());
        user.setOrders(orders);
    }
}
