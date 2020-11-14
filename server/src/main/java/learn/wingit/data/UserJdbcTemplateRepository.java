package learn.wingit.data;

import learn.wingit.data.mappers.OrderMapper;
import learn.wingit.data.mappers.PlaneMapper;
import learn.wingit.data.mappers.UserMapper;
import learn.wingit.models.Order;
import learn.wingit.models.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserJdbcTemplateRepository implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        final String sql = "select user_id, role_id, username, password_hash, `email`, `phone`, `address`, `company` from `user`;";

        jdbcTemplate.query(sql, new UserMapper());

        return null;
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
        return false;
    }

    @Override
    public boolean deleteUser(int userId) {
        return false;
    }

    private void addOrders(User user) {
        final String sql =
                "select o.order_id, o.user_id, o.order_date, o.total_cost from `order` o " +
                        "inner join `user` u on u.user_id = o.user_id where o.user_id = ?;";

        var orders = jdbcTemplate.query(sql, new OrderMapper(), user.getUserId());
        user.setOrders(orders);
    }
}
