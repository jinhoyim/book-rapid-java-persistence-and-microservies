package book.apress.rapidjavapersistencemicroservice.ch04.eshop.converter;

import book.apress.rapidjavapersistencemicroservice.ch04.eshop.orders.model.type.CustomerAddress;
import jakarta.persistence.AttributeConverter;

public class CustomerAddressConverter implements AttributeConverter<CustomerAddress, String> {

    @Override
    public String convertToDatabaseColumn(CustomerAddress customerAddress) {
        if (customerAddress == null) {
            return "";
        }
        return customerAddress.getStreet() + "," +
                customerAddress.getCity() + "," +
                customerAddress.getCountry();
    }

    @Override
    public CustomerAddress convertToEntityAttribute(String value) {
        CustomerAddress customerAddress = null;
        if (value != null && value.contains(",")) {
            String[] tokens = value.split(",");
            customerAddress = new CustomerAddress();
            customerAddress.setStreet(tokens[0]);
            customerAddress.setCity(tokens[1]);
            customerAddress.setCountry(tokens[2]);
        }
        return customerAddress;
    }
}
