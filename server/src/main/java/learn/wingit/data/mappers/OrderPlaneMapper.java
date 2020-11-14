package learn.wingit.data.mappers;

import learn.wingit.models.Order_Plane;

import org.springframework.jdbc.core.RowMapper;
import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderPlaneMapper implements RowMapper<Order_Plane> {


    @Override
    public Order_Plane mapRow(ResultSet resultSet, int i) throws SQLException {
        Order_Plane op = new Order_Plane();
        op.setOrderId(resultSet.getInt("order_id"));
        op.setPlane_Id(resultSet.getInt("plane_id"));
        op.setNumber_ordered(resultSet.getInt("number_ordered"));

        PlaneMapper planeMapper = new PlaneMapper();
        op.setPlane(planeMapper.mapRow(resultSet, i));

        OrderMapper orderMapper = new OrderMapper();
        op.setOrder(orderMapper.mapRow(resultSet, i));
        return op;
    }
}
