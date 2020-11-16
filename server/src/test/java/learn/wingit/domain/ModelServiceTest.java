package learn.wingit.domain;

import learn.wingit.data.ModelRepository;
import learn.wingit.models.Manufacturer;
import learn.wingit.models.PlaneModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ModelServiceTest {

    @Autowired
    ModelService service;

    @MockBean
    ModelRepository repository;

    @Test
    void shouldAddModel() {
        PlaneModel model = makeModel();
        PlaneModel mock = makeModel();

        when(repository.add(model)).thenReturn(mock);

        Result<PlaneModel> result = service.add(model);
        assertNotNull(result);
        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotAddNull() {
        Result<PlaneModel> result = service.add(null);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddNullManufacturer() {
        PlaneModel model = makeModel();
        model.setManufacturer(null);

        Result<PlaneModel> result = service.add(model);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddBlankName() {
        PlaneModel model = makeModel();
        model.setName("");

        Result<PlaneModel> result = service.add(model);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldUpdateModel() {
        PlaneModel model = makeModel();
        model.setModel_id(1);

        when(repository.update(model)).thenReturn(true);

        Result<PlaneModel> result = service.update(model);
        assertNotNull(result);
        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotUpdateMissingId() {
        PlaneModel model = makeModel();
        model.setModel_id(99999);

        when(repository.update(model)).thenReturn(false);

        Result<PlaneModel> result = service.update(model);
        assertNotNull(result);
        assertFalse(result.isSuccess());
    }


    private PlaneModel makeModel(){
        PlaneModel model = new PlaneModel();
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setManufacturer_id(1);
        manufacturer.setName("Boeing");
        model.setName("Test-150");
        model.setManufacturer(manufacturer);
        return model;
    }
}