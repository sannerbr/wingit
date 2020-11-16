package learn.wingit.domain;


import learn.wingit.data.PlaneRepository;
import learn.wingit.models.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PlaneServiceTest {

    @Autowired
    PlaneService service;

    @MockBean
    PlaneRepository repository;

    @Test
    void shouldAddPlane() {
        Plane plane = makePlane();
        Plane mock = makePlane();

        when(repository.add(plane)).thenReturn(mock);

        Result<Plane> result = service.add(plane);

        assertNotNull(result);
        assertTrue(result.isSuccess());
    }
    @Test
    void shouldNotAddNullPlane() {
        Result<Plane> result = service.add(null);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddMissingModel() {
        Plane plane = makePlane();
        plane.setModel(null);
        Result<Plane> result = service.add(plane);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddMissingSize() {
        Plane plane = makePlane();
        plane.setSize(null);
        Result<Plane> result = service.add(plane);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddMissingType() {
        Plane plane = makePlane();
        plane.setType(null);
        Result<Plane> result = service.add(plane);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddNullPrice() {
        Plane plane = makePlane();
        plane.setPrice(null);
        Result<Plane> result = service.add(plane);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddNegativePrice() {
        Plane plane = makePlane();
        plane.setPrice(BigDecimal.valueOf(-1));
        Result<Plane> result = service.add(plane);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldUpdatePlane() {
        Plane plane = makePlane();
        plane.setPlane_id(1);

        when(repository.update(plane)).thenReturn(true);

        Result<Plane> result = service.update(plane);
        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    @Test
    void shouldNotUpdateMissingModel() {
        Plane plane = makePlane();
        PlaneModel model = makeModel();
        model.setModel_id(0);
        plane.setModel(model);
        Result<Plane> result = service.update(plane);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotUpdateBadId() {
        Plane plane = makePlane();
        plane.setPlane_id(0);
        Result<Plane> result = service.update(plane);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddDuplicatePlane() {
        when(repository.findAll()).thenReturn(listOfPlanes());
        Plane plane = makePlane();
        plane.getModel().setModel_id(7);
        plane.getModel().setName("AC-130");
        plane.setSize(Size.SMALL);
        Result<Plane> result = service.add(plane);
        assertFalse(result.isSuccess());
        assertEquals("This plane already exists", result.getMessages().get(0));
    }

    @Test
    void shouldNotUpdateToDuplicatePlane() {
        when(repository.findAll()).thenReturn(listOfPlanes());
        Plane plane = makePlane();
        plane.setPlane_id(2);
        Result<Plane> result = service.update(plane);
        assertFalse(result.isSuccess());
        assertEquals("This plane already exists", result.getMessages().get(0));
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

    private List<Plane> listOfPlanes() {
        List<Plane> list = new ArrayList<>();
        Plane plane1 = makePlane();
        plane1.setPlane_id(1);
        Plane plane2 = makePlane();
        plane2.setPlane_id(2);
        plane2.getModel().setModel_id(7);
        plane2.getModel().setName("AC-130");
        plane2.setSize(Size.SMALL);
        list.add(plane1);
        list.add(plane2);
        return list;
    }
}