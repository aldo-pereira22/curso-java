package br.com.microservices.orchestrated.productvalidationservice.core.service;


import br.com.microservices.orchestrated.productvalidationservice.config.exception.ValidationException;
import br.com.microservices.orchestrated.productvalidationservice.core.dto.Event;
import br.com.microservices.orchestrated.productvalidationservice.core.producer.KafkaProducer;
import br.com.microservices.orchestrated.productvalidationservice.core.repository.ProductRepository;
import br.com.microservices.orchestrated.productvalidationservice.core.repository.ValidationRepository;
import br.com.microservices.orchestrated.productvalidationservice.core.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static org.springframework.util.StringUtils.isEmpty;

@Slf4j
@Service
@AllArgsConstructor
public class ProductValidationService {

    private static final String CURRENT_SOURCE="PRODUCT_VALIDATION_SERVICE";

    private final JsonUtil jsonUtil;
    private final KafkaProducer kafkaProducer;
    private final ProductRepository productRepository;
    private final ValidationRepository validationRepository;

    public void validateExistingProducts(Event event){
        try {
            checkCurrentValidation(event);
            createValidation(event);
            handleSuccess(event);
        }catch (Exception ex){
            log.error("Error trying to validate products", ex);
            handleFailCurrenteNotExecuted(event, ex.getMessage());
        }


        kafkaProducer.sendEvent(jsonUtil.toJson(event));
    }


    private void validateProductsInformed(Event event){
        if( isEmpty (event.getPayload()) || isEmpty(event.getPayload().getProducts()) ){
            throw new ValidationException("Product list is empty!");
        }

        if(isEmpty(event.getPayload().getId()) || isEmpty(event.getPayload().getTransactionId())){
            throw new ValidationException("OrderID and TransactionId must be informed!");
        }
    }

    private void checkCurrentValidation(Event event){
        validateProductsInformed(event);
        
    }



}



















