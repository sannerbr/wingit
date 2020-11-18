package learn.wingit.data;

import learn.wingit.models.Order;
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
        Order result = repository.add(order);
        assertNotNull(result);
        assertEquals(1, order.getUserId());
        assertEquals(4, order.getOrderId());
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
}