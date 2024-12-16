package book.apress.rapidjavapersistencemicroservice.ch04.eshop.orders.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String name;
}
