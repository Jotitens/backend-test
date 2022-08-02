package com.akka.backendtest.adapter;

import com.akka.backendtest.existingApis.api.DefaultApi;
import com.akka.backendtest.existingApis.model.ProductDetail;
import com.akka.backendtest.port.SimilarIdsPort;
import com.akka.backendtest.utils.UtilsLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.akka.backendtest.utils.Constants.LEVEL_DEBUG;
import static java.util.Optional.ofNullable;

@AllArgsConstructor
@Component
public class ProductApiAdapter implements SimilarIdsPort {

    ProductApiInvoker productApiInvoker;
    DefaultApi defaultApi;
    ObjectMapper objectMapper;

    @Override
    public Set<String> getProductSimilarIds(String productId) throws Exception {
        Set<String> response = (productApiInvoker.invoke(() -> defaultApi.getProductSimilaridsWithHttpInfo(productId)).getBody());
        logAction(productId, response);
        return response;

    }


    @Override
    public ProductDetail getProductDetail(String productId)  {
        ResponseEntity<ProductDetail> response = null;
        try {
            response = (productApiInvoker.invoke(() -> defaultApi.getProductProductIdWithHttpInfo(productId)));
            logAction(productId, response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ofNullable(response).map(ResponseEntity::getBody).orElse(null);
    }

    public void logAction(String request, Object response) throws JsonProcessingException {
        UtilsLog.customLog(LEVEL_DEBUG, "Request: " + request + " / " + "Response: " + objectMapper.writeValueAsString(response)) ;
    }
}
