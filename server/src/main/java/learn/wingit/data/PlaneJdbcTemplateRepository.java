package learn.wingit.data;

import learn.wingit.data.mappers.OrderPlaneMapper;
import learn.wingit.data.mappers.PlaneMapper;
import learn.wingit.models.Plane;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class PlaneJdbcTemplateRepository implements PlaneRepository {

    private final JdbcTemplate jdbcTemplate;

    public PlaneJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Plane> findAll() {
        final String sql = "select " +
                "plane_id, model_id,size_id, type_id, " +
                "price, quantity, seating_capacity, " +
                "height, length, wingspan, hidden, " +
                "`range`, `description` " +
                "from plane;";


        return jdbcTemplate.query(sql, new PlaneMapper());
    }

    @Override
    public List<Plane> findAllForUser() {
        final String sql = "select " +
                "plane_id, model_id,size_id, type_id, " +
                "price, quantity, seating_capacity, " +
                "height, length, wingspan, hidden, " +
                "`range`, `description` " +
                "from plane " +
                "where hidden = 0;";


        return jdbcTemplate.query(sql, new PlaneMapper());
    }

    @Override
    public List<Plane> findByType(int type_id) {
        final String sql = "select " +
                "plane_id, model_id,size_id, type_id, " +
                "price, quantity, seating_capacity, " +
                "height, length, wingspan, hidden, " +
                "`range`, `description` " +
                "from plane " +
                "where type_id = ?;";
        return jdbcTemplate.query(sql, new PlaneMapper(), type_id);
    }

    @Override
    public Plane findById(int plane_id) {
        final String sql = "select " +
                "plane_id, model_id,size_id, type_id, " +
                "price, quantity, seating_capacity, " +
                "height, length, wingspan, hidden, " +
                "`range`, `description` " +
                "from plane " +
                "where plane_id = ?;";
        Plane plane = jdbcTemplate.query(sql, new PlaneMapper(), plane_id)
                .stream().findFirst().orElse(null);

        if (plane != null) {
            addOrders(plane);
        }

        return plane;
    }

    @Override
    public Plane add(Plane plane) {
        final String sql = "insert into plane(" +
                "model_id,size_id, type_id, " +
                "price, quantity, seating_capacity, " +
                "height, length, wingspan, hidden, " +
                "`range`, `description`) " +
                "values (?,?,?,?,?,?,?,?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected =jdbcTemplate.update(connection -> {
            PreparedStatement ps= connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, plane.getModel_id());
            ps.setInt(2, plane.getSize_id());
            ps.setInt(3, plane.getType_id());
            ps.setBigDecimal(4, plane.getPrice());
            ps.setInt(5, plane.getQuantity());
            ps.setInt(6, plane.getSeating_capacity());
            ps.setInt(7, plane.getHeight());
            ps.setInt(8, plane.getLength());
            ps.setInt(9, plane.getWingspan());
            ps.setBoolean(10, plane.isHidden());
            ps.setInt(11, plane.getRange());
            ps.setString(12, plane.getDescription());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        plane.setPlane_id(keyHolder.getKey().intValue());
        return plane;
    }

    @Override
    public boolean update(Plane plane) {
        final String sql = "update plane set " +
                "model_id = ?, " +
                "size_id = ?, " +
                "type_id = ?, " +
                "price = ?, " +
                "quantity = ?, " +
                "seating_capacity = ?, " +
                "height = ?, " +
                "length = ?, " +
                "wingspan = ?, " +
                "hidden = ?, " +
                "`range` = ?, " +
                "`description` = ? " +
                "where plane_id = ?;";
        return jdbcTemplate.update(sql,
                plane.getModel_id(), plane.getSize_id(),
                plane.getType_id(), plane.getPrice(),
                plane.getQuantity(), plane.getSeating_capacity(),
                plane.getHeight(), plane.getLength(),
                plane.getWingspan(), plane.isHidden(), plane.getRange(),
                plane.getDescription(), plane.getPlane_id()
                ) > 0;
    }

    @Override
    public boolean delete(int planeId) {
        return jdbcTemplate.update("update plane set hidden = 1 where plane_id = ?;", planeId) > 0;
    }

    private void addOrders(Plane plane) {
        final String sql = "select op.order_id, op.plane_id, op.number_ordered, o.order_id, o.user_id, " +
                "o.order_date, o.total_cost, "  +
                "p.plane_id, p.model_id,size_id, p.type_id, " +
                "p.price, p.quantity, p.seating_capacity, " +
                "p.height, p.length, p.wingspan, p.hidden, " +
                "p.`range`, p.`description` " +
                "from order_plane op " +
                "inner join `order` o on op.order_id = o.order_id " +
                "inner join plane p on op.plane_id = p.plane_id " +
                "where op.plane_id = ?;";
        var planeOrders =jdbcTemplate.query(sql, new OrderPlaneMapper(), plane.getPlane_id());
        plane.setOrders(planeOrders);
    }


}
