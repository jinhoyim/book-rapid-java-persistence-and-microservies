package book.apress.rapidjavapersistencemicroservice.ch04.eshop;

import book.apress.rapidjavapersistencemicroservice.ch04.eshop.history.repository.PurchaseHistoryRepository;
import book.apress.rapidjavapersistencemicroservice.ch04.eshop.orders.model.Customer;
import book.apress.rapidjavapersistencemicroservice.ch04.eshop.orders.model.dto.CustomerDto;
import book.apress.rapidjavapersistencemicroservice.ch04.eshop.orders.repository.CustomerRepository;
import book.apress.rapidjavapersistencemicroservice.ch04.eshop.orders.repository.OrderRepository;
import book.apress.rapidjavapersistencemicroservice.ch04.eshop.service.impl.CustomerService;
import book.apress.rapidjavapersistencemicroservice.ch04.eshop.service.impl.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@Slf4j
public class EshopApplication implements CommandLineRunner {

    final ProductService productService;

    final CustomerService customerService;

    final OrderRepository orderRepository;

    final CustomerRepository customerRepository;

    final PurchaseHistoryRepository purchaseHistoryRepository;

    public EshopApplication(ProductService productService, CustomerService customerService, OrderRepository orderRepository, CustomerRepository customerRepository, PurchaseHistoryRepository purchaseHistoryRepository) {
        this.productService = productService;
        this.customerService = customerService;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.purchaseHistoryRepository = purchaseHistoryRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(EshopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        customerService.registerNewCustomers();
        productService.registerNewProducts();
        productService.purchase(1l, 1l, 2, 400);
        productService.saveHistory(1l, 1l);

        log.info("Customers {}", customerService.findAll());
        log.info("Products {}", productService.findAll());
        log.info("Orders {}", orderRepository.findAll());
        log.info("PurchaseHistory {}", purchaseHistoryRepository.findAll());

        nPlusOneExample();
        constructorMappingExample();
    }

    private void constructorMappingExample() {
        List<CustomerDto> customers = customerRepository.findAllCustomers();
        log.info("Customers List with Constructors mapped query results {}", customers);
    }

    private void nPlusOneExample() {
        List<Customer> customerList = customerRepository.findCustomersWithOrderDetails();
        log.info("Customers List with Order Details {}", customerList);
    }
}
