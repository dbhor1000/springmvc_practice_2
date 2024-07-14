package javacode_dk.springmvc_practice.repository;

import javacode_dk.springmvc_practice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
