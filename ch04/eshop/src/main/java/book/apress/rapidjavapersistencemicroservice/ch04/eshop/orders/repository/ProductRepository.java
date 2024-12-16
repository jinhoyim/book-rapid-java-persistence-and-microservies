package book.apress.rapidjavapersistencemicroservice.ch04.eshop.orders.repository;

import book.apress.rapidjavapersistencemicroservice.ch04.eshop.orders.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
