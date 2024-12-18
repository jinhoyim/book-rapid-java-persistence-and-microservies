package book.apress.rapidjavapersistencemicroservice.eshopservice.repository;

import book.apress.rapidjavapersistencemicroservice.eshopservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
