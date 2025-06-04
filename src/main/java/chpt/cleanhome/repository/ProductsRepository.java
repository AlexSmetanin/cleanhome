package chpt.cleanhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import chpt.cleanhome.entity.Product;

public interface ProductsRepository extends JpaRepository<Product, Long> {
}
