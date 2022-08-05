package com.akka.backendtest.service;

import com.akka.backendtest.adapter.ProductApiAdapter;
import com.akka.backendtest.controller.model.SimilarProducts;
import com.akka.backendtest.exception.NotFoundException;
import com.akka.backendtest.existingApis.api.DefaultApi;
import com.akka.backendtest.utils.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.aspectj.apache.bcel.classfile.ConstantString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class SimilarProductServiceTest {

    @Mock
    ProductApiAdapter productApiAdapter;

    @InjectMocks
    SimilarProductsServiceImpl similarProductsService;

    @BeforeEach
    public void setUpMockServices() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(productApiAdapter.getProductSimilarIds(Constants.ONE)).thenReturn(Set.of(Constants.ONE));
    }

    @Test
    public void getSimilarProductsTestOK() throws Exception {
        when(productApiAdapter.getProductDetail(Constants.ONE)).thenReturn(buildProductDetail());
        SimilarProducts similarProducts = similarProductsService.getSimilarProducts(Constants.ONE);
        assertNotNull(similarProducts);

    }

    @Test
    public void getSimilarProductsJsonProcessingException() throws Exception {
        doThrow(JsonProcessingException.class).when(productApiAdapter).getProductDetail(Constants.ONE);
        Assertions.assertThrows(JsonProcessingException.class, () -> similarProductsService.getSimilarProducts(Constants.ONE));

    }

    @Test
    public void getSimilarProductsResourceAccessException() throws Exception {
        doThrow(ResourceAccessException.class).when(productApiAdapter).getProductDetail(Constants.ONE);
        Assertions.assertThrows(ResourceAccessException.class, () -> similarProductsService.getSimilarProducts(Constants.ONE));

    }

    @Test
    public void getSimilarProductsHttpClientErrorException() throws Exception {
        doThrow(HttpClientErrorException.class).when(productApiAdapter).getProductDetail(Constants.ONE);
        Assertions.assertThrows(HttpClientErrorException.class, () -> similarProductsService.getSimilarProducts(Constants.ONE));

    }

    @Test
    public void getSimilarProductsTestNotFound() throws Exception {
        Assertions.assertThrows(NotFoundException.class, () -> similarProductsService.getSimilarProducts(Constants.TWO));

    }

    private com.akka.backendtest.existingApis.model.ProductDetail buildProductDetail(){
        return com.akka.backendtest.existingApis.model.ProductDetail.builder()
                .id(Constants.ONE)
                .name(Constants.SHIRT)
                .price(BigDecimal.ONE)
                .availability(true)
                .build();
    }

}
