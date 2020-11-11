package learn.wingit.data;

import learn.wingit.data.mappers.PlaneMapper;
import learn.wingit.models.Plane;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

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
        return null;
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
