package learn.wingit.data;

import learn.wingit.data.mappers.OrderMapper;
import learn.wingit.models.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class OrderJdbcTemplateRepository implements OrderRepository {
    private final JdbcTemplate jdbcTemplate;

    public OrderJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Order> findAll() {
        final String sql = "select order_id, user_id, order_date, total_cost from `order`;";
        return jdbcTemplate.query(sql, new OrderMapper());
    }

    @Override
    public Order findById(int orderId) {
        final String sql = "select order_id, user_id, order_date, total_cost from `order` o where o.order_id = ?;";

        Order order = jdbcTemplate.query(sql, new OrderMapper(), orderId).stream().findFirst().orElse(null);

        return order;
    }

    @Override
    public Order add(Order order) {
        final String sql = "insert into `order` (user_id, order_date, total_cost) values (?,?,?);";

        KeyHolder keyholder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getUserId());
            ps.setDate(2, Date.valueOf(order.getOrderDate()));
            ps.setBigDecimal(3, order.getTotalCost());
            return ps;
        }, keyholder);
        if(rowsAffected <= 0) {
            return null;
        }

        order.setOrderId(keyholder.getKey().intValue());
        return order;
    }

    @Override
    public boolean update(Order order) {
        final String sql = "update `order` set order_date = ?, total_cost = ? where order_id = ?;";

        return jdbcTemplate.update(sql,
                order.getOrderDate(),
                order.getTotalCost(),
                order.getOrderId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int orderId) {
        return jdbcTemplate.update("delete from `order` where order_id = ?;", orderId) > 0;
    }

}
