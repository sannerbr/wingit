package learn.wingit.domain;

import learn.wingit.data.ManufacturerRepository;
import learn.wingit.models.Manufacturer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ManufacturerServiceTest {

    @Autowired
    ManufacturerService service;

    @MockBean
    ManufacturerRepository repository;

    @Test
    void shouldAddManufacturer() {
        Manufacturer manufacturer = new Manufacturer();
        Manufacturer mock = new Manufacturer();
        manufacturer.setName("Test");
        mock.setName("Test");

        when(repository.add(manufacturer)).thenReturn(mock);

        Result<Manufacturer> result = service.add(manufacturer);

        assertNotNull(result);
        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotAddNull() {
        Result<Manufacturer> result = service.add(null);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddBlankName() {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("");

        Result<Manufacturer> result = service.add(manufacturer);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldUpdateManufacturer() {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Test");
        manufacturer.setManufacturer_id(1);

        when(repository.update(manufacturer)).thenReturn(true);

        Result<Manufacturer> result = service.update(manufacturer);

        assertNotNull(result);
        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotUpdateBlankName() {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("");
        manufacturer.setManufacturer_id(1);

        Result<Manufacturer> result = service.update(manufacturer);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotUpdateBadId() {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Test");
        manufacturer.setManufacturer_id(-1);

        Result<Manufacturer> result = service.update(manufacturer);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotUpdateMissingId() {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Test");
        manufacturer.setManufacturer_id(99999);

        when(repository.update(manufacturer)).thenReturn(false);

        Result<Manufacturer> result = service.update(manufacturer);
        assertFalse(result.isSuccess());
    }

}