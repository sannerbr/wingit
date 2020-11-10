package learn.wingit.data;

import learn.wingit.models.Manufacturer;
import learn.wingit.models.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ManufacturerRepository {
    List<Manufacturer> findAll();

    Manufacturer findById();

    Manufacturer add(Manufacturer manufacturer);

    boolean update(Manufacturer manufacturer);

    @Transactional
    boolean deleteById(int manufacturerId);
}
