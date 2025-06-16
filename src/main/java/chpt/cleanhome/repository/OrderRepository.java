package chpt.cleanhome.repository;

import chpt.cleanhome.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> id(Long id);

}
