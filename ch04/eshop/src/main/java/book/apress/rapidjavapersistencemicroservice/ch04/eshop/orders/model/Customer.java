package book.apress.rapidjavapersistencemicroservice.ch04.eshop.orders.model;

import book.apress.rapidjavapersistencemicroservice.ch04.eshop.converter.CustomerAddressConverter;
import book.apress.rapidjavapersistencemicroservice.ch04.eshop.orders.model.type.CustomerAddress;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString(exclude = {"orders"})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String name;

    private String email;

    private String password;

    private LocalDateTime createdAt;

    @Column
    @Convert(converter = CustomerAddressConverter.class)
    private CustomerAddress customerAddress;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    Set<Order> orders;
}
