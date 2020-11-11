package learn.wingit.data;

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
public class PlaneJdbcTemplateRepository implements PlaneRepository{

    private final JdbcTemplate jdbcTemplate;

    public PlaneJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Plane> findAll() {
        final String sql = "select " +
                "plane_id, model_id,size_id, type_id, " +
                "price, quantity, seating_capacity, " +
                "height, length, wingspan, " +
                "`range`, `description` " +
                "from plane;";


        return jdbcTemplate.query(sql, new PlaneMapper());
    }

    @Override
    public List<Plane> findByType(int type_id) {
        final String sql = "select " +
                "plane_id, model_id,size_id, type_id, " +
                "price, quantity, seating_capacity, " +
                "height, length, wingspan, " +
                "`range`, `description` " +
                "from plane" +
                "where type_id = ?;";
        return jdbcTemplate.query(sql, new PlaneMapper(), type_id);
    }

    @Override
    public Plane findById(int plane_id) {
        final String sql = "select " +
                "plane_id, model_id,size_id, type_id, " +
                "price, quantity, seating_capacity, " +
                "height, length, wingspan, " +
                "`range`, `description` " +
                "from plane" +
                "where type_id = ?;";
        return jdbcTemplate.query(sql, new PlaneMapper(), plane_id)
                .stream().findFirst().orElse(null);
    }

    @Override
    public Plane add(Plane plane) {
        final String sql = "insert into plane(" +
                "model_id,size_id, type_id, " +
                "price, quantity, seating_capacity, " +
                "height, length, wingspan, " +
                "`range`, `description`) " +
                "values (?,?,?,?,?,?,?,?,?,?,?)";

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
            ps.setInt(10, plane.getRange());
            ps.setString(11, plane.getDescription());
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
        return false;
    }

    @Override
    public boolean delete(Plane plane) {
        return false;
    }
}
