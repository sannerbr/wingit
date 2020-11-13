package learn.wingit.domain;

import learn.wingit.data.OrderRepository;
import learn.wingit.models.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;

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
    void shouldNotUpdateOrder() {

    }

    private Order makeOrder() {
        Order order = new Order();
        order.setOrderDate(LocalDate.of(2018, 9, 23));
        order.setTotalCost(BigDecimal.valueOf(2323.52));
        order.setUserId(1);
        return order;
    }
}