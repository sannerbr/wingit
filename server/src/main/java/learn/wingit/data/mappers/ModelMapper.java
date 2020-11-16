package learn.wingit.data.mappers;

import learn.wingit.models.Manufacturer;
import learn.wingit.models.PlaneModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ModelMapper implements RowMapper<PlaneModel> {


    @Override
    public PlaneModel mapRow(ResultSet resultSet, int i) throws SQLException {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setManufacturer_id(resultSet.getInt("manufacturer_id"));
        manufacturer.setName(resultSet.getString("manufacturer_name"));

        PlaneModel model = new PlaneModel();
        model.setModel_id(resultSet.getInt("model_id"));
        model.setName(resultSet.getString("model_name"));
        model.setManufacturer(manufacturer);
        return model;
    }
}
