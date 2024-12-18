package book.apress.rapidjavapersistencemicroservice.inventoryservice.repository;

import book.apress.rapidjavapersistencemicroservice.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
