package book.apress.rapidjavapersistencemicroservice.ch04.eshop.orders.model.type;

import lombok.Data;

@Data
public class CustomerAddress {

    private String street;
    private String city;
    private String country;
}
