package book.apress.rapidjavapersistencemicroservice.ch04.eshop.orders.repository;

import book.apress.rapidjavapersistencemicroservice.ch04.eshop.orders.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
