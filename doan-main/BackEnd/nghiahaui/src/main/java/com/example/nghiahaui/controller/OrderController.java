package com.example.nghiahaui.controller;

import java.util.HashSet;
import java.util.List;

import com.example.nghiahaui.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nghiahaui.entity.Order;
import com.example.nghiahaui.model.request.CreateOrderRequest;
import com.example.nghiahaui.model.response.MessageResponse;
import com.example.nghiahaui.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "*",maxAge = 3600)
public class OrderController {
    @Autowired
    private OrderService orderService;


    @GetMapping("/")
    @Operation(summary="Lấy ra danh sách đặt hàng")
    public ResponseEntity<List<Order>> getList(){
        List<Order> list = orderService.getList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/user")
    @Operation(summary="Lấy ra danh sách đặt hàng của người dùng bằng username")
    public ResponseEntity<List<Order>> getListByUser(@RequestParam("username") String username){
        List<Order> list = orderService.getOrderByUser(username);

        return ResponseEntity.ok(list);
    }

    @PostMapping("/create")
    @Operation(summary="Đặt hàng sản phẩm")
    public ResponseEntity<?> placeOrder(@RequestBody CreateOrderRequest request){

        orderService.placeOrder(request);

        return ResponseEntity.ok(new MessageResponse("Order Placed Successfully!"));
    }
    @GetMapping("/{orderId}")
    @Operation(summary = "Lấy ra thông tin đơn hàng theo ID")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
        List<OrderDetail> orderDetails = orderService.getOrderDetailsByOrderId(orderId);

        if (order != null) {
            order.setOrderDetails(new HashSet<>(orderDetails)); // Convert to Set if needed
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
