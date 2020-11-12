package learn.wingit.domain;

import learn.wingit.data.OrderRepository;
import learn.wingit.data.PlaneRepository;
import learn.wingit.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {

    private enum ValidationMode {
        ADD, UPDATE
    }

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private Validator validator;

    public List<Order> findAll() {
        return repository.findAll();
    }

    public List<Order> findOrdersByUsername(String username) {
        return repository.findOrdersByUsername(username);
    }

    public Order findById(int order_id) {
        repository.findById(order_id);
        return null;
    }

    public Result<Order> add(Order order) {
        Result<Order> result = validate(order, ValidationMode.ADD);

        if(result.isSuccess()) {
            order = repository.add(order);
            result.setPayload(order);
        }
        return result;
    }

    public Result<Order> update(Order order) {
        Result<Order> result = validate(order, ValidationMode.UPDATE);
        if(result.isSuccess()) {
            if(repository.update(order)) {
                result.setPayload(order);
            } else {
                String msg = String.format("Order Id: %s not found!", order.getOrderId());
                result.addMessage(msg, ResultType.NOT_FOUND);
            }
        }

        return result;
    }

    public boolean deleteById(int order_id) {
        return repository.deleteById(order_id);
    }

    private Result<Order> validate(Order order, ValidationMode validationMode) {
        Result<Order> result = new Result<>();

        if(order == null) {
            result.addMessage("order cannot be null", ResultType.INVALID);
        } else if(validationMode == ValidationMode.ADD && order.getOrderId() > 0) {
            result.addMessage("Order ID cannot be set for order", ResultType.INVALID);
        } else if(validationMode == ValidationMode.UPDATE && order.getOrderId() <= 0) {
            result.addMessage("Order ID is required", ResultType.INVALID);
        }

        if(result.isSuccess()) {
            Set<ConstraintViolation<Order>> violations = validator.validate(order);

            if(!violations.isEmpty()) {
                for(ConstraintViolation<Order> violation : violations) {
                    result.addMessage(violation.getMessage(), ResultType.INVALID);
                }
            }
        }
        return result;
    }




}
