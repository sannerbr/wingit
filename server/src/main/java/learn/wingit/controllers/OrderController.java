package learn.wingit.controllers;


import learn.wingit.domain.OrderService;
import learn.wingit.domain.Result;
import learn.wingit.models.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:8081"})
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public List<Order> findAll() {
        return service.findAll();

    }

    @GetMapping("/{orderId}")
    public Order findById(@PathVariable int orderId) {
        return service.findById(orderId);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Order order) {
        Result<Order> result = service.add(order);
        if(result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }



}
