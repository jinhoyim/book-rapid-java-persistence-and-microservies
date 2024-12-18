package book.apress.rapidjavapersistencemicroservice.eshopservice.repository;

import book.apress.rapidjavapersistencemicroservice.eshopservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
