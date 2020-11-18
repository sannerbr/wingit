package learn.wingit.domain;

import learn.wingit.data.OrderRepository;
import learn.wingit.models.*;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class OrderServiceTest {
    @Autowired
    OrderService service;

    @MockBean
    OrderRepository repository;

    @Test
    void shouldAddOrder() {
        Order order = makeOrder();
        Order mock = makeOrder();

        when(repository.add(order)).thenReturn(mock);

        Result<Order> result = service.add(order);
        assertNotNull(result);
        assertTrue(result.isSuccess());
    }

    @Test
    void shouldFindById() {
        Order order = new Order(1, 1, LocalDate.of(2020, 1, 1), BigDecimal.valueOf(200.00), null);
        when(repository.findById(1)).thenReturn(order);
        Order result = service.findById(1);
        assertNotNull(result);
        assertEquals(1, order.getUserId());
    }

    @Test
    void shouldNotAddOrderWhenIdIsSet() {
        Order order = makeOrder();
        order.setOrderId(1);
        Order mock = makeOrder();
        mock.setOrderId(1);

        when(repository.add(order)).thenReturn(mock);

        Result<Order> result = service.add(order);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddInvalidCost() {
        Order order = makeOrder();
        order.setTotalCost(BigDecimal.valueOf(-2300.23));
        Order mock = makeOrder();
        mock.setTotalCost(BigDecimal.valueOf(-2300.23));

        when(repository.add(order)).thenReturn(mock);

        Result<Order> result = service.add(order);
        assertFalse(result.isSuccess());
        assertEquals("[Total cost must be greater than or equal to 0.00]", result.getMessages().toString());
    }

    @Test
    void shouldNotAddWhenDateIsInFuture() {
        Order order = makeOrder();
        order.setOrderDate(LocalDate.of(2030, 9, 12));
        Order mock = makeOrder();
        mock.setOrderDate(LocalDate.of(2030, 9, 12));
        when(repository.add(order)).thenReturn(mock);
        Result<Order> result = service.add(order);
        assertFalse(result.isSuccess());
        assertEquals("[Order Date cannot be in the future]", result.getMessages().toString());
    }

    @Test
    void shouldUpdateOrder() {
        Order order = makeOrder();
        order.setOrderId(2);

        when(repository.update(order)).thenReturn(true);
        Result<Order> result = service.update(order);
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    @Test
    void shouldNotUpdateOrderWithNullCost() {
        Order order = makeOrder();
        order.setOrderId(2);
        order.setTotalCost(null);
        Result<Order> result = service.update(order);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotUpdateWithDateInFuture() {
        Order order = makeOrder();
        order.setOrderId(2);
        order.setOrderDate(LocalDate.of(2022, 4, 4));
        Result<Order> result = service.update(order);
        assertEquals("[Order Date cannot be in the future]", result.getMessages().toString());
    }

    @Test
    void shouldDelete() {
        when(repository.deleteById(2)).thenReturn(true);
        boolean result = repository.deleteById(2);
        assertTrue(result);
    }

    @Test
    void shouldNotDelete() {
        when(repository.deleteById(59000)).thenReturn(false);
        boolean result = repository.deleteById(59000);
        assertFalse(result);
    }

    @Test
    void shouldNotAddOrderForUnavailableQuantity() {
        Order order = makeOrder();
        order.setPlanes(List.of(makePlane()));
        Result<Order> result = service.add(order);
        assertFalse(result.isSuccess());
    }

    private Order makeOrder() {
        Order order = new Order();
        order.setOrderDate(LocalDate.of(2018, 9, 23));
        order.setTotalCost(BigDecimal.valueOf(2323.52));
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