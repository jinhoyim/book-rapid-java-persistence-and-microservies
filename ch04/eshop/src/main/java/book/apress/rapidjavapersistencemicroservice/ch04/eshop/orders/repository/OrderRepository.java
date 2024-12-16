package book.apress.rapidjavapersistencemicroservice.ch04.eshop.orders.repository;

import book.apress.rapidjavapersistencemicroservice.ch04.eshop.orders.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
