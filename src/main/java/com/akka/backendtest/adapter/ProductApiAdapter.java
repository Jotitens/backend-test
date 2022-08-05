package com.akka.backendtest.adapter;

import com.akka.backendtest.existingApis.api.DefaultApi;
import com.akka.backendtest.existingApis.model.ProductDetail;
import com.akka.backendtest.adapter.port.SimilarIdsPort;
import com.akka.backendtest.utils.Constants;
import com.akka.backendtest.utils.UtilsLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.net.SocketTimeoutException;
import java.util.Set;

import static com.akka.backendtest.utils.Constants.*;

@AllArgsConstructor
@Component
public class ProductApiAdapter implements SimilarIdsPort {

    private DefaultApi defaultApi;
    private ObjectMapper objectMapper;

    @Override
    public Set<String> getProductSimilarIds(String productId) throws Exception {
        Set<String> response = defaultApi.getProductSimilarids(productId);
        logAction(productId, response, Constants.SIMILAR_IDS_SERVICE);
        return response;
    }



    @Override
    public ProductDetail getProductDetail(String productId)  {
        ProductDetail response = null;
        try {
            response = defaultApi.getProductProductId(productId);
            logAction(productId, response,  Constants.PRODUCT_DETAIL_SERVICE);
        } catch (JsonProcessingException e) {
            UtilsLog.customLog(LEVEL_ERROR, e.getLocalizedMessage());
        } catch (ResourceAccessException | HttpClientErrorException e){
            UtilsLog.customLog(LEVEL_INFO, e.getLocalizedMessage());
            return null;
        }
        return response;
    }

    public void logAction(String request, Object response, String service) throws JsonProcessingException {
        UtilsLog.customLog(LEVEL_DEBUG, "Calling "+ service +" - Request: " + request + " / " + "Response: " + objectMapper.writeValueAsString(response)) ;
    }

}
