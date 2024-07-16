package javacode_dk.springmvc_practice.repository.MVC1;

import javacode_dk.springmvc_practice.model.MVC1.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
