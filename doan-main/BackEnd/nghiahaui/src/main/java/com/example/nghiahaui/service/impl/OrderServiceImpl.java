package com.example.nghiahaui.service.impl;

import java.util.List;

import com.example.nghiahaui.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.nghiahaui.entity.Order;
import com.example.nghiahaui.entity.OrderDetail;
import com.example.nghiahaui.entity.User;
import com.example.nghiahaui.exception.NotFoundException;
import com.example.nghiahaui.model.request.CreateOrderDetailRequest;
import com.example.nghiahaui.model.request.CreateOrderRequest;
import com.example.nghiahaui.repository.OrderDetailRepository;
import com.example.nghiahaui.repository.OrderRepository;
import com.example.nghiahaui.repository.UserRepository;
import com.example.nghiahaui.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void placeOrder(CreateOrderRequest request) {
        Order order = new Order();
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new NotFoundException("Not Found User With Username:" + request.getUsername()));

        order.setFirstname(request.getFirstname());
        order.setLastname(request.getLastname());
        order.setCountry(request.getCountry());
        order.setAddress(request.getAddress());
        order.setTown(request.getTown());
        order.setState(request.getState());
        order.setPostCode(request.getPostCode());
        order.setEmail(request.getEmail());
        order.setPhone(request.getPhone());
        order.setNote(request.getNote());

        long totalPrice = 0;
        for (CreateOrderDetailRequest rq : request.getOrderDetails()) {
            OrderDetail orderDetail = new OrderDetail();

            // Các thông tin khác
            orderDetail.setName(rq.getName());
            orderDetail.setPrice(rq.getPrice());
            orderDetail.setQuantity(rq.getQuantity());
            orderDetail.setSubTotal(rq.getPrice() * rq.getQuantity());
            orderDetail.setOrder(order);

            totalPrice += orderDetail.getSubTotal();
            orderDetailRepository.save(orderDetail);
        }


        order.setTotalPrice(totalPrice);
        order.setUser(user);
        orderRepository.save(order);
    }

    @Override
    public List<Order> getList() {
        return orderRepository.findAll(Sort.by("id").descending());
    }

    @Override
    public List<Order> getOrderByUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Not Found User With Username:" + username));

        return orderRepository.getOrderByUser(user.getId());
    }

    @Override
    public OrderDetail getOrderDetail(Long orderId) {
        return orderDetailRepository.findById(orderId).orElse(null);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOrderId(Long orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }
}
