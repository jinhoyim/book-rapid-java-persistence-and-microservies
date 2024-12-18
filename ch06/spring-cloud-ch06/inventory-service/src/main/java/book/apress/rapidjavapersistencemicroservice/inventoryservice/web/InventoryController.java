package book.apress.rapidjavapersistencemicroservice.inventoryservice.web;

import book.apress.rapidjavapersistencemicroservice.inventoryservice.model.Inventory;
import book.apress.rapidjavapersistencemicroservice.inventoryservice.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/api/inventory/{productId}")
    public Inventory getInventory(@PathVariable("productId") Long productId) {
        log.info("Inventory request for product: {}", productId);
        Inventory inventory = inventoryService.getInventory(productId);
        log.info("Inventory: {}", inventory);
        return inventory;
    }
}
