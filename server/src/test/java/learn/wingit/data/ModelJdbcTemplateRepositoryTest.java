package learn.wingit.data;

import learn.wingit.models.Manufacturer;
import learn.wingit.models.PlaneModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ModelJdbcTemplateRepositoryTest {

    @Autowired
    ModelJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<PlaneModel> models = repository.findAll();
        assertNotNull(models);
        assertNotNull(models.get(0).getManufacturer());
        assertEquals(6, models.size());
    }

    @Test
    void shouldFindById() {
        PlaneModel model = repository.findById(1);
        assertNotNull(model);
        assertEquals("747", model.getName());
        assertNotNull(model.getManufacturer());
        assertEquals("Boeing", model.getManufacturer().getName());
    }

    @Test
    void shouldAddModel() {
        PlaneModel model = new PlaneModel();
        model.setName("Test");
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setManufacturer_id(1);
        model.setManufacturer(manufacturer);

        PlaneModel actual = repository.add(model);
        assertNotNull(actual);
        assertEquals(7, repository.findAll().size());
    }

    @Test
    void shouldUpdateModel() {
        PlaneModel model = new PlaneModel();
        model.setName("Test");
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setManufacturer_id(1);
        model.setManufacturer(manufacturer);
        model.setModel_id(2);

        boolean actual = repository.update(model);
        assertTrue(actual);
        assertEquals(6, repository.findAll().size());
    }
}