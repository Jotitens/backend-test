package com.akka.backendtest.adapter;

import com.akka.backendtest.utils.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.web.client.RestClientException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class ProductApiAdapterTest {

    @Mock
    com.akka.backendtest.existingApis.api.DefaultApi defaultApi;

    @InjectMocks
    ProductApiAdapter productApiAdapter;

    @BeforeEach
    public void setUp() throws JsonProcessingException {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProductSimilarIdsReturnOK() throws Exception {
        when(defaultApi.getProductSimilarids(anyString())).thenReturn(anySet());
        Set<String> response = productApiAdapter.getProductSimilarIds(Constants.ONE);
        assertNotNull(response);

    }

    @Test
    public void testGetProductSimilarIdsException() throws Exception {
        doThrow(RestClientException.class).when(defaultApi).getProductSimilarids(anyString());
        Exception thrown = Assertions.assertThrows(Exception.class, () ->
            productApiAdapter.getProductSimilarIds(Constants.ONE));
    }

    @Test
    public void testGetProductDetailReturnOK(){
        when(defaultApi.getProductProductId(anyString())).thenReturn(new com.akka.backendtest.existingApis.model.ProductDetail());
        com.akka.backendtest.existingApis.model.ProductDetail response = productApiAdapter.getProductDetail(Constants.ONE);
        assertNotNull(response);

    }

    @Test
    public void testGetProductDetailException() {
        doThrow(RestClientException.class).when(defaultApi).getProductProductId(anyString());
        Assertions.assertThrows(RestClientException.class, () -> productApiAdapter.getProductDetail(Constants.ONE));

    }

}
