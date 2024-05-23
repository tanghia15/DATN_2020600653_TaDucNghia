package com.example.nghiahaui.controller;

import com.example.nghiahaui.entity.OrderDetail;
import com.example.nghiahaui.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/order_detail")
@CrossOrigin(origins = "*",maxAge = 3600)
public class OrderDetailController {
    @Autowired
    private OrderService orderService;


    @GetMapping("/{orderId}")
    @Operation(summary = "Lấy ra order_detail cua mot don hang cu the")
    public ResponseEntity<OrderDetail> getOrderDetail(@PathVariable Long orderId) {
        OrderDetail orderDetail = orderService.getOrderDetail(orderId);

        if (orderDetail != null) {
            return ResponseEntity.ok(orderDetail);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
