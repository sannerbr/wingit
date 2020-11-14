package learn.wingit.data;

import learn.wingit.data.mappers.OrderMapper;
import learn.wingit.data.mappers.OrderPlaneMapper;
import learn.wingit.data.mappers.PlaneMapper;
import learn.wingit.models.Order;
import learn.wingit.models.Plane;
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
        List<Order> orders = jdbcTemplate.query(sql, new OrderMapper());
        for(Order o : orders) {
            addPlanes(o);
        }
        return orders;
    }

    @Override
    public List<Order> findOrdersByUsername(String username) {
        final String sql = "select o.order_id, o.user_id, o.order_date, o.total_cost from `order` o " +
                "inner join `user` u on u.user_id = o.user_id" +
                " where u.username = ?;";
        List<Order> orders = jdbcTemplate.query(sql, new OrderMapper(), username);;
        for(Order o : orders) {
            addPlanes(o);
        }
        return orders;
    }

    @Override
    public Order findById(int orderId) {
        final String sql = "select order_id, user_id, order_date, total_cost from `order` o where o.order_id = ?;";

        Order order = jdbcTemplate.query(sql, new OrderMapper(), orderId).stream().findFirst().orElse(null);

        if(order != null) {
            addPlanes(order);
        }

        return order;
    }



    @Override
    @Transactional
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

        //Not Need? order should be passed in with list of planes
        //addPlanes(order);

        order.setOrderId(keyholder.getKey().intValue());

        final String sql2 = "insert into order_plane (order_id, plane_id, number_ordered) values (?,?,?);";
        for(Plane p : order.getPlanes()) {
            rowsAffected = jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql2);
                ps.setInt(1, order.getOrderId());
                ps.setInt(2, p.getPlane_id());
                ps.setInt(3,p.getQuantity());
                return ps;
            });
        }
        if(rowsAffected <= 0) {
            return null;
        }
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
        jdbcTemplate.update("delete from order_plane where order_id = ?;", orderId);
        return jdbcTemplate.update("delete from `order` where order_id = ?;", orderId) > 0;
    }

    private void addPlanes(Order order) {
        final String sql =
                "select p.plane_id, p.model_id, mo.`name` as model_name, ma.`name` as manufacturer_name, " +
                "ma.manufacturer_id, p.size_id, p.type_id, " +
                "p.price, op.number_ordered as quantity, p.seating_capacity, " +
                "p.height, p.length, p.wingspan, p.hidden, " +
                "p.`range`, p.`description` " +
                "from order_plane op " +
                "inner join plane p on op.plane_id = p.plane_id " +
                "inner join model mo on p.model_id = mo.model_id " +
                "inner join manufacturer ma on mo.manufacturer_id = ma.manufacturer_id " +
                "where op.order_id = ?;";


        var planes = jdbcTemplate.query(sql, new PlaneMapper(), order.getOrderId());
        order.setPlanes(planes);
    }
}
