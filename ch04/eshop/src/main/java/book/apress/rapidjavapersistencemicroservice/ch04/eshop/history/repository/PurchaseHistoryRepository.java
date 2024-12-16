package book.apress.rapidjavapersistencemicroservice.ch04.eshop.history.repository;

import book.apress.rapidjavapersistencemicroservice.ch04.eshop.history.model.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {
}
