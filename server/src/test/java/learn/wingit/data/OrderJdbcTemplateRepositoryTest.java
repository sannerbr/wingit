package learn.wingit.data;

import learn.wingit.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class OrderJdbcTemplateRepositoryTest {

    @Autowired
    PlaneJdbcTemplateRepository planeRepository;

    @Autowired
    OrderJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAllOrders() {
        List<Order> result = repository.findAll();
        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    void shouldFindOrdersByUsername() {
        List<Order> result = repository.findOrdersByUsername("customer");
        assertNotNull(result);
        assertTrue(result.size() >= 2);
    }

    @Test
    void shouldFindById() {
      Order order = repository.findById(2);
      assertNotNull(order);
      assertEquals(1, order.getPlanes().size());
      assertEquals(2, order.getOrderId());
    }

    @Test
    void shouldAddOrderSuccessfully() {
        Order order = makeOrder();
        order.setPlanes(List.of(makePlane()));
        Order result = repository.add(order);
        assertNotNull(result);
        assertEquals(1, order.getUserId());
        assertEquals(4, order.getOrderId());
        assertEquals(1, planeRepository.findById(2).getQuantity());
    }

    @Test
    void shouldUpdateOrder() {
        Order order = new Order(2, 1, LocalDate.of(2020,2,2), BigDecimal.valueOf(300.23), null);
        boolean result = repository.update(order);
        assertTrue(result);
        assertEquals(BigDecimal.valueOf(300.23), repository.findById(2).getTotalCost());
    }


    @Test
    void shouldDeleteSuccessfully() {
        boolean result = repository.deleteById(2);
        assertTrue(result);
    }

    @Test
    void shouldNotDelete() {
        boolean result = repository.deleteById(89009);
        assertFalse(result);
    }




    private Order makeOrder() {
        Order order = new Order();
        order.setOrderDate(LocalDate.of(2018, 9, 23));
        order.setTotalCost(BigDecimal.valueOf(2323.50));
        order.setUserId(1);
        return order;
    }

    private Plane makePlane(){
        Plane plane = new Plane();
        plane.setPlane_id(2);
        plane.setModel(makeModel());
        plane.setSize(Size.MEDIUM);
        plane.setType(Type.PRIVATE);
        plane.setPrice(BigDecimal.valueOf(1000));
        plane.setQuantity(1);
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