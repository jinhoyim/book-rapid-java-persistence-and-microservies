package book.apress.rapidjavapersistencemicroservice.eshopservice.repository;

import book.apress.rapidjavapersistencemicroservice.eshopservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
