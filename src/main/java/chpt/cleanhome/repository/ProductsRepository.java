package chpt.cleanhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import chpt.cleanhome.entity.Product;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Product, Long> {
    List<Product> id(Long id);
}
