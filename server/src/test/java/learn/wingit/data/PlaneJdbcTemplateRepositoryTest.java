package learn.wingit.data;

import learn.wingit.models.Plane;
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
        assertTrue(planes.get(3).isHidden());
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

    }
}