package book.apress.rapidjavapersistencemicroservice.eshopservice.service.impl;

import book.apress.rapidjavapersistencemicroservice.eshopservice.model.Product;
import book.apress.rapidjavapersistencemicroservice.eshopservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void registerNewProducts() {
        Product product = new Product();
        product.setName("Superb Java");
        product.setPrice(400);
        product.setQuantity(3);
        productRepository.save(product);
    }
}
