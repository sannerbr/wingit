package learn.wingit.data;

import learn.wingit.models.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepository {

    List<Order> findAll();

    Order findById(int orderId);

    Order add(Order order);

    boolean update(Order order);

    @Transactional
    boolean deleteById(int orderId);

}
