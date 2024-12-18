package book.apress.rapidjavapersistencemicroservice.eshopservice.service.impl;

import book.apress.rapidjavapersistencemicroservice.eshopservice.model.Customer;
import book.apress.rapidjavapersistencemicroservice.eshopservice.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
        customer.setName("Raj Malhotra");
        customer.setEmail("raj.malhotra@example.com");
        customer.setPassword("password");
        customerRepository.saveAndFlush(customer);
    }
}
