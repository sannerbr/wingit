package learn.wingit.data.mappers;

import learn.wingit.models.Manufacturer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ManufacturerMapper implements RowMapper<Manufacturer> {
    @Override
    public Manufacturer mapRow(ResultSet resultSet, int i) throws SQLException {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setManufacturer_id(resultSet.getInt("manufacturer_id"));
        manufacturer.setName(resultSet.getString("name"));
        return manufacturer;
    }
}
