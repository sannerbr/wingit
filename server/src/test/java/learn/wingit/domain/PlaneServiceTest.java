package learn.wingit.domain;


import learn.wingit.data.PlaneRepository;
import learn.wingit.models.Plane;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

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
        plane.setModel_id(0);
        Result<Plane> result = service.add(plane);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddMissingSize() {
        Plane plane = makePlane();
        plane.setSize_id(0);
        Result<Plane> result = service.add(plane);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddMissingType() {
        Plane plane = makePlane();
        plane.setType_id(0);
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
        plane.setModel_id(0);
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


    private Plane makePlane(){
        Plane plane = new Plane();
        plane.setModel_id(5);
        plane.setSize_id(2);
        plane.setType_id(3);
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
}