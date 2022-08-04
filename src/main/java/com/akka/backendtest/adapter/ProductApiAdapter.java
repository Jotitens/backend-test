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
import static com.akka.backendtest.utils.Constants.LEVEL_WARN;
import static java.util.Optional.ofNullable;

@AllArgsConstructor
@Component
public class ProductApiAdapter implements SimilarIdsPort {

    private ProductApiInvoker productApiInvoker;
    private DefaultApi defaultApi;
    private ObjectMapper objectMapper;

    @Override
    public Set<String> getProductSimilarIds(String productId) throws Exception {
        ResponseEntity<Set<String>> response = (productApiInvoker.invoke(() -> defaultApi.getProductSimilaridsWithHttpInfo(productId)));
        logAction(productId, response, "SimilarIdsService");
        return ofNullable(response).map(ResponseEntity::getBody).orElse(null);

    }


    @Override
    public ProductDetail getProductDetail(String productId)  {
        ResponseEntity<ProductDetail> response = null;
        try {
            response = (productApiInvoker.invoke(() -> defaultApi.getProductProductIdWithHttpInfo(productId)));
            logAction(productId, response, "ProductDetailService");
        } catch (JsonProcessingException e) {
            UtilsLog.customLog(LEVEL_WARN, e.getLocalizedMessage());
        } catch (Exception e) {
            UtilsLog.customLog(LEVEL_WARN, e.getLocalizedMessage());
            return null;
        }

        return ofNullable(response).map(ResponseEntity::getBody).orElse(null);
    }

    public void logAction(String request, Object response, String service) throws JsonProcessingException {
        UtilsLog.customLog(LEVEL_DEBUG, "Calling "+ service +" - Request: " + request + " / " + "Response: " + objectMapper.writeValueAsString(response)) ;
    }

}
