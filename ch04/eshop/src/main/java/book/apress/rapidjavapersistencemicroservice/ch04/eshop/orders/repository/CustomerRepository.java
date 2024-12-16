package book.apress.rapidjavapersistencemicroservice.ch04.eshop.orders.repository;

import book.apress.rapidjavapersistencemicroservice.ch04.eshop.orders.model.Customer;
import book.apress.rapidjavapersistencemicroservice.ch04.eshop.orders.model.dto.CustomerDto;
import book.apress.rapidjavapersistencemicroservice.ch04.eshop.orders.model.projection.CustomerProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.orders")
    List<Customer> findCustomersWithOrderDetails();

    @Query("SELECT new " +
            "book.apress.rapidjavapersistencemicroservice.ch04.eshop.orders.model.dto.CustomerDto(c.customerId, c.name) " +
            "FROM Customer c")
    List<CustomerDto> findAllCustomers();

    @Query("SELECT c from Customer c")
    List<CustomerProjection> findCustomers();

    CustomerProjection findByName(String name);
}
