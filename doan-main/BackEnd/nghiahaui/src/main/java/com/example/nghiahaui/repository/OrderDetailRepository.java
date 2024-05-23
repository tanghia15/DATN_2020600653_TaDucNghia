package com.example.nghiahaui.repository;

import com.example.nghiahaui.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nghiahaui.entity.OrderDetail;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
    List<OrderDetail> findByOrder(Order order);

    List<OrderDetail> findByOrderId(Long orderId);
}
