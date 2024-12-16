package book.apress.rapidjavapersistencemicroservice.ch04.eshop.orders.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "CustomerOrder")
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Long productId;

    private Long customerId;

    private int quantity;

    private int price;
}
