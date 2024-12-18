package book.apress.rapidjavapersistencemicroservice.inventoryservice.service;

import book.apress.rapidjavapersistencemicroservice.inventoryservice.model.Inventory;
import book.apress.rapidjavapersistencemicroservice.inventoryservice.repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory getInventory(Long productId) {
        log.info("Inventory lookup request for productId: {}", productId);
        Optional<Inventory> inventory = inventoryRepository.findById(productId);
        return inventory.orElse(Inventory.builder()
                .inventoryId(11L)
                .productId(productId)
                .price(200)
                .quantity(2)
                .build());
    }
}
