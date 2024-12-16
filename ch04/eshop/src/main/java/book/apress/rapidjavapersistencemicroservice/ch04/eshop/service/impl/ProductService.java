package book.apress.rapidjavapersistencemicroservice.ch04.eshop.service.impl;

import book.apress.rapidjavapersistencemicroservice.ch04.eshop.history.model.PurchaseHistory;
import book.apress.rapidjavapersistencemicroservice.ch04.eshop.history.repository.PurchaseHistoryRepository;
import book.apress.rapidjavapersistencemicroservice.ch04.eshop.orders.model.Order;
import book.apress.rapidjavapersistencemicroservice.ch04.eshop.orders.model.Product;
import book.apress.rapidjavapersistencemicroservice.ch04.eshop.orders.repository.OrderRepository;
import book.apress.rapidjavapersistencemicroservice.ch04.eshop.orders.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ProductService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final PurchaseHistoryRepository purchaseHistoryRepository;

    public ProductService(
            OrderRepository orderRepository,
            ProductRepository productRepository,
            PurchaseHistoryRepository purchaseHistoryRepository
    ) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.purchaseHistoryRepository = purchaseHistoryRepository;
    }

    @Transactional
    public Boolean purchase(Long productId, Long customerId, int quantity, int price) {
        Boolean success = Boolean.TRUE;
        Order order = new Order();
        order.setCustomerId(customerId);
        order.setProductId(productId);
        order.setPrice(price);
        order.setQuantity(quantity);
        orderRepository.save(order);
        return success;
    }

    @Transactional
    public void saveHistory(Long customerId, Long productId) {
        PurchaseHistory history = new PurchaseHistory();
        history.setCustomerId(customerId);
        history.setProductId(productId);
        history.setCreatedDate(LocalDate.now());
        history.setCreatedDateTime(LocalDateTime.now());
        purchaseHistoryRepository.save(history);
    }

    public void registerNewProducts() {
        Product product = new Product();
        product.setName("Superb Java");
        product.setPrice(400);
        product.setQuantity(3);
        productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
