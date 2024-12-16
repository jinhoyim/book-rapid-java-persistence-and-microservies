package book.apress.rapidjavapersistencemicroservice.ch04.eshop.history.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "PurchaseHistory")
@ToString
public class PurchaseHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private Long productId;

    private LocalDate createdDate;

    private LocalDateTime createdDateTime;
}
