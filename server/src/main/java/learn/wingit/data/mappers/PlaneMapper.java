package learn.wingit.data.mappers;

import learn.wingit.models.*;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PlaneMapper implements RowMapper<Plane> {


    @Override
    public Plane mapRow(ResultSet resultSet, int i) throws SQLException {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setManufacturer_id(resultSet.getInt("manufacturer_id"));
        manufacturer.setName(resultSet.getString("manufacturer_name"));

        PlaneModel model = new PlaneModel();
        model.setModel_id(resultSet.getInt("model_id"));
        model.setName(resultSet.getString("model_name"));
        model.setManufacturer(manufacturer);

        Plane plane = new Plane();
        plane.setPlane_id(resultSet.getInt("plane_id"));
        plane.setModel(model);
        plane.setSize(Size.getSizeById(resultSet.getInt("size_id")));
        plane.setType(Type.getTypeById(resultSet.getInt("type_id")));
        plane.setPrice(BigDecimal.valueOf(resultSet.getInt("price")));
        plane.setQuantity(resultSet.getInt("quantity"));
        plane.setSeating_capacity(resultSet.getInt("seating_capacity"));
        plane.setHeight(resultSet.getInt("height"));
        plane.setLength(resultSet.getInt("length"));
        plane.setWingspan(resultSet.getInt("wingspan"));
        plane.setRange(resultSet.getInt("range"));
        plane.setDescription(resultSet.getString("description"));
        plane.setHidden(resultSet.getBoolean("hidden"));

        return plane;
    }
}
