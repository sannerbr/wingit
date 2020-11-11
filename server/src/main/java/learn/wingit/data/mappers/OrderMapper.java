package learn.wingit.data.mappers;

import learn.wingit.models.Order;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        Order order = new Order();
        order.setOrderId(resultSet.getInt("order_id"));
        order.setUserId(resultSet.getInt("user_id"));
        order.setOrderDate(resultSet.getDate("order_date").toLocalDate());
        order.setTotalCost(resultSet.getBigDecimal("total_cost"));
        return order;
    }
}
