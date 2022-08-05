package com.akka.backendtest.adapter;

import com.akka.backendtest.existingApis.api.DefaultApi;
import com.akka.backendtest.existingApis.model.ProductDetail;
import com.akka.backendtest.adapter.port.SimilarIdsPort;
import com.akka.backendtest.utils.UtilsLog;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import java.util.Set;

import static com.akka.backendtest.utils.Constants.*;

@AllArgsConstructor
@Component
public class ProductApiAdapter implements SimilarIdsPort {

    private DefaultApi defaultApi;
    private ObjectMapper objectMapper;

    @Override
    public Set<String> getProductSimilarIds(String productId) throws Exception {
        return defaultApi.getProductSimilarids(productId);
    }

    @Override
    public ProductDetail getProductDetail(String productId)  {
        ProductDetail response = null;
        try {
            response = defaultApi.getProductProductId(productId);
        } catch (ResourceAccessException | HttpClientErrorException e){
            UtilsLog.customLog(LEVEL_INFO, e.getLocalizedMessage());
            return null;
        }
        return response;
    }

}
