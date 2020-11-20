package learn.wingit.data;

import learn.wingit.data.mappers.ManufacturerMapper;
import learn.wingit.data.mappers.ModelMapper;
import learn.wingit.models.Manufacturer;
import learn.wingit.models.PlaneModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ManufacturerJdbcTemplateRepository implements ManufacturerRepository {

    private final JdbcTemplate jdbcTemplate;

    public ManufacturerJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Manufacturer> findAll() {
        final String sql = "select manufacturer_id, `name` from manufacturer;";
        return jdbcTemplate.query(sql, new ManufacturerMapper());
    }

    @Override
    public Manufacturer findById(int manufacturerId) {
        final String sql = "select manufacturer_id, `name` from manufacturer where manufacturer_id = ?;";
        return jdbcTemplate.query(sql, new ManufacturerMapper(), manufacturerId).stream().findFirst().orElse(null);
    }

    @Override
    public Manufacturer add(Manufacturer manufacturer) {
        final String sql = "insert into manufacturer(`name`) values(?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected =jdbcTemplate.update(connection -> {
            PreparedStatement ps= connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, manufacturer.getName());
            return ps;
        }, keyHolder);
        if (rowsAffected <= 0){
            return null;
        }

        manufacturer.setManufacturer_id(keyHolder.getKey().intValue());
        return manufacturer;
    }

    @Override
    public boolean update(Manufacturer manufacturer) {
        final String sql = "update manufacturer set `name` = ? where manufacturer_id = ?;";
        return jdbcTemplate.update(sql, manufacturer.getName(), manufacturer.getManufacturer_id()) > 0;
    }

    @Override
    public boolean deleteById(int manufacturerId) {
        jdbcTemplate.update("delete from order_plane where plane_id in (select plane_id " +
                "from plane where model_id in (select model_id from model where manufacturer_id = ?));", manufacturerId);
        jdbcTemplate.update("delete from plane where model_id in (" +
                "select model_id from model where manufacturer_id = ?);", manufacturerId);
        jdbcTemplate.update("delete from model where manufacturer_id = ?;", manufacturerId);
        return jdbcTemplate.update("delete from manufacturer where manufacturer_id = ?;", manufacturerId) > 0;
    }
}
