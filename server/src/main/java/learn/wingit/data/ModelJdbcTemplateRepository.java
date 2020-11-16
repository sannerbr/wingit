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
public class ModelJdbcTemplateRepository implements ModelRepository{

    private final JdbcTemplate jdbcTemplate;

    public ModelJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<PlaneModel> findAll() {
        final String sql = "select mo.model_id, mo.`name` as model_name, " +
                "mo.manufacturer_id, ma.`name` as manufacturer_name " +
                "from model mo inner join manufacturer ma on mo.manufacturer_id = ma. manufacturer_id;";
        //        for (PlaneModel model : models) {
//            final String sql2 = "select manufacturer_id, `name` from manufacturer where manufacturer_id = ?;";
//            model.setManufacturer(jdbcTemplate.query(sql2, new ManufacturerMapper(),
//                    model.getManufacturer().getManufacturer_id()).stream().findFirst().orElse(null));
//        }
        return jdbcTemplate.query(sql, new ModelMapper());
    }

    @Override
    public PlaneModel findById(int modelId) {
        final String sql = "select mo.model_id, mo.`name` as model_name, " +
                "mo.manufacturer_id, ma.`name` as manufacturer_name " +
                "from model mo inner join manufacturer ma on mo.manufacturer_id = ma. manufacturer_id " +
                "where model_id = ?;";
        //        final String sql2 = "select manufacturer_id, `name` from manufacturer where manufacturer_id = ?;";
//        Manufacturer manufacturer = jdbcTemplate.query(sql2, new ManufacturerMapper(),
//                model.getManufacturer().getManufacturer_id()).stream().findFirst().orElse(null);
//        model.setManufacturer(manufacturer);
        return jdbcTemplate.query(sql, new ModelMapper(), modelId).stream().findFirst().orElse(null);
    }

    @Override
    public PlaneModel add(PlaneModel model) {
        final String sql = "insert into model(`name`, manufacturer_id) values(?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected =jdbcTemplate.update(connection -> {
            PreparedStatement ps= connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, model.getName());
            ps.setInt(2, model.getManufacturer().getManufacturer_id());
            return ps;
        }, keyHolder);
        if (rowsAffected <= 0){
            return null;
        }

        model.setModel_id(keyHolder.getKey().intValue());
        return model;
    }

    @Override
    public boolean update(PlaneModel model) {
        final String sql = "update model set `name` = ?, manufacturer_id = ? where model_id = ?;";
        return jdbcTemplate.update(sql, model.getName(), model.getManufacturer().getManufacturer_id()
                , model.getModel_id()) > 0;
    }

    @Override
    public boolean delete(int modelId) {
        return false;
    }
}
