package learn.wingit.data;

import learn.wingit.models.Plane;
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


    @Test
    void shouldFindAll(){
        List<Plane> planes = repository.findAll();
        assertNotNull(planes);
        assertEquals(BigDecimal.valueOf(100.00), planes.get(0).getPrice());
    }

}