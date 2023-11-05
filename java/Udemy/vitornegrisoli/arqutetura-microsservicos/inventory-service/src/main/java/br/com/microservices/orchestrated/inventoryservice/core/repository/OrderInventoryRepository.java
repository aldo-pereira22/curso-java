package br.com.microservices.orchestrated.inventoryservice.core.repository;

import br.com.microservices.orchestrated.inventoryservice.core.model.Inventory;
import br.com.microservices.orchestrated.inventoryservice.core.model.OrderInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderInventoryRepository extends JpaRepository<Inventory, Integer> {

    Boolean existByOrderIdAndTransactionId(String orderId, String transactionId);
    List<Optional<OrderInventory>> findByOrderIdAndTransactionId(String orderId, String transactionId);

}
