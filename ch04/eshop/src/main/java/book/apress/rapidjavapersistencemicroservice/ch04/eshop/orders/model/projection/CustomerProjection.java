package book.apress.rapidjavapersistencemicroservice.ch04.eshop.orders.model.projection;

import org.springframework.beans.factory.annotation.Value;

public interface CustomerProjection {

    String getName();
    String getEmail();

    @Value("#{target.name + '_' + target.email}")
    String getCustomerNameWithEmail();
}
