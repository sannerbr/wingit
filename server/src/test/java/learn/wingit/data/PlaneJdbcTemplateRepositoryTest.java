package learn.wingit.data;

import learn.wingit.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PlaneJdbcTemplateRepositoryTest {

    @Autowired
    PlaneJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<Plane> planes = repository.findAll();
        assertNotNull(planes);
        assertEquals(BigDecimal.valueOf(100), planes.get(0).getPrice());
        assertTrue(planes.get(2).isHidden());
        assertEquals(4, planes.size());
    }

    @Test
    void shouldFindById() {
        Plane plane = repository.findById(1);
        assertNotNull(plane);
        assertEquals(BigDecimal.valueOf(100), plane.getPrice());
    }

    @Test
    void shouldFindByType() {
        List<Plane> planes = repository.findByType(1);
        assertNotNull(planes);
        assertEquals(BigDecimal.valueOf(100), planes.get(0).getPrice());
    }

    @Test
    void shouldAddPlane() {
        Plane actual = repository.add(makePlane());
        assertNotNull(actual);
        assertEquals(5, actual.getPlane_id());
    }

    @Test
    void shouldUpdatePlane() {
        Plane plane = makePlane();
        plane.setPlane_id(1);
        PlaneModel model = makeModel();
        model.setModel_id(5);
        plane.setModel(model);
        plane.setDescription("A box");
        boolean success = repository.update(plane);

        assertTrue(success);
        assertEquals("A box", repository.findById(1).getDescription());
    }

    @Test
    void shouldHidePlane() {
        boolean success = repository.delete(1);
        assertTrue(success);
        assertTrue(repository.findById(1).isHidden());
    }




    private Plane makePlane(){
        Plane plane = new Plane();
        plane.setModel(makeModel());
        plane.setSize(Size.MEDIUM);
        plane.setType(Type.PRIVATE);
        plane.setPrice(BigDecimal.valueOf(1000));
        plane.setQuantity(10);
        plane.setSeating_capacity(10);
        plane.setHeight(10);
        plane.setLength(10);
        plane.setWingspan(10);
        plane.setRange(10);
        plane.setDescription("This is a box");
        plane.setHidden(false);
        return plane;
    }

    private PlaneModel makeModel() {
        PlaneModel model = new PlaneModel();
        model.setModel_id(6);
        model.setName("Douglas DC-3");
        model.setManufacturer(makeMan());
        return model;
    }

    private Manufacturer makeMan() {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setManufacturer_id(2);
        manufacturer.setName("Airbus");
        return manufacturer;
    }



}