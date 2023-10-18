package br.com.microservices.orchestrated.orderservice.core.service;

import br.com.microservices.orchestrated.orderservice.config.exception.ValidationException;
import br.com.microservices.orchestrated.orderservice.core.document.Event;
import br.com.microservices.orchestrated.orderservice.core.dto.EventFilters;
import br.com.microservices.orchestrated.orderservice.core.repository.EventRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class EventService {

    private final EventRepository repository;


    public void notifyEnding(Event event){
        event.setOrderId(event.getOrderId());
        event.setCreatedAt(LocalDateTime.now());
        save(event);
        log.info("Order {} with saga notified! TransactionId: {}", event.getOrderId(), event.getTransactionId());
    }

    public List<Event> findAll(){
        return repository.findAllByOrderByCreatedAtDesc();
    }

    public Event findByFilters(EventFilters filters){
        validateEmptyFilters(filters);
        if(!ObjectUtils.isEmpty(filters.getOrderId())){
            return findByOrderId(filters.getOrderId()) ;
        }else {
            return findByTransactionId(filters.getTransactionId());
        }
    }






    public Event save(Event event){
        return  repository.save(event);
    }


    private void validateEmptyFilters(EventFilters filters){
        if(ObjectUtils.isEmpty(filters.getOrderId()) && ObjectUtils.isEmpty(filters.getTransactionId()) ){
            throw new ValidationException("OrderId or TransactionId must be informed.");
        }
    }

    private Event findByOrderId(String orderid){
        return repository
                .findTop1ByOrderIdOrderByCreatedAtDesc(orderid)
                .orElseThrow( () -> new ValidationException("Event not found by orderId"));

    }

    private Event findByTransactionId(String transactionId){
        return repository
                .findTop1ByTransactionIdOrderByCreatedAtDesc(transactionId)
                .orElseThrow( () -> new ValidationException("Event not found by transactionId"));

    }
}
