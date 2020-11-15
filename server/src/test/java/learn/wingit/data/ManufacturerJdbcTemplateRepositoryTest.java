package learn.wingit.data;

import learn.wingit.models.Manufacturer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ManufacturerJdbcTemplateRepositoryTest {

    @Autowired
    ManufacturerJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll(){
        List<Manufacturer> manufacturers = repository.findAll();
        assertNotNull(manufacturers);
        assertEquals(2, manufacturers.size());
        assertNotNull(manufacturers.get(0).getModels());
        assertEquals(3, manufacturers.get(0).getModels().size());
    }

    @Test
    void shouldFindById() {
        Manufacturer manufacturer = repository.findById(2);
        assertNotNull(manufacturer);
        assertEquals("Airbus", manufacturer.getName());
        assertNotNull(manufacturer.getModels());
        assertEquals(3, manufacturer.getModels().size());
    }

    @Test
    void shouldAddManufacturer() {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Test");
        Manufacturer actual = repository.add(manufacturer);
        assertNotNull(actual);
        assertEquals(3, repository.findAll().size());
    }

    @Test
    void shouldUpdateManufacturer() {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Test");
        boolean actual = repository.update(manufacturer);
        assertTrue(actual);
        assertEquals(2, repository.findAll().size());
    }

    @Test
    void shouldDeleteManufacturer() {
        boolean success = repository.deleteById(1);
        assertTrue(success);
    }
}