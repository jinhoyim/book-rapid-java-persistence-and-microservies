package book.apress.rapidjavapersistencemicroservice.ch04.eshop.orders.repository;

import book.apress.rapidjavapersistencemicroservice.ch04.eshop.orders.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.orders")
    List<Customer> findCustomersWithOrderDetails();
}
