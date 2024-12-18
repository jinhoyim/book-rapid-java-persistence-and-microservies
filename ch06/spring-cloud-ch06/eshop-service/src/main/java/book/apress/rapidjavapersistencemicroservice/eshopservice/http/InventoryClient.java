package book.apress.rapidjavapersistencemicroservice.eshopservice.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;

@FeignClient("INVENTORY-SERVICE")
public interface InventoryClient {

    @GetMapping(value = "/inventory/api/inventory/{productId}")
    HashMap<String, Integer> getInventory(@PathVariable("productId") Long productId);
}
