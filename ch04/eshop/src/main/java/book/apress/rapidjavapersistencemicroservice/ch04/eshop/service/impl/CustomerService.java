package book.apress.rapidjavapersistencemicroservice.ch04.eshop.service.impl;

import book.apress.rapidjavapersistencemicroservice.ch04.eshop.orders.model.Customer;
import book.apress.rapidjavapersistencemicroservice.ch04.eshop.orders.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void registerNewCustomers() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setEmail("john@example.org");
        customer.setPassword("my-password");
        customer.setCreatedAt(LocalDateTime.now());
        customerRepository.saveAndFlush(customer);
        log.info("All registered customers: " + customerRepository.findAll());
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
