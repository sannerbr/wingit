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
    void shouldFindById() {
      Order order = repository.findById(2);
      assertNotNull(order);
      assertEquals(200, order.getTotalCost());
    }

    @Test
    void shouldAddOrderSuccessfully() {
        Order order = makeOrder();
        Order result = repository.add(order);
        assertNotNull(result);
        assertEquals(1, order.getUserId());
    }

    @Test
    void shouldNotAddOrderSuccessfully() {

    }

    @Test
    void shouldUpdateOrder() {

    }

    @Test
    void shouldNotUpdateOrder() {

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