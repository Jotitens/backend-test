package com.akka.backendtest.service;

import com.akka.backendtest.adapter.ProductApiAdapter;
import com.akka.backendtest.adapter.ProductApiInvoker;
import com.akka.backendtest.existingApis.api.DefaultApi;
import com.akka.backendtest.existingApis.invoker.ApiClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class SimilarProductServiceTest {
//    DefaultApi defaultApi;
//    ProductApiAdapter productApiAdapter;
//    ProductApiInvoker productApiInvoker;
//
////    @BeforeEach
////    public void setUp(){
////        defaultApi = new DefaultApi();
////        defaultApi.setApiClient(new ApiClient());
////        productApiAdapter = new ProductApiAdapter(new ProductApiInvoker(), defaultApi);
////    }
//
//
//
//    @Test
//    public void testService(){
//
//        SimilarProductService service = new SimilarProductService(productApiAdapter);
//        try {
//            service.getSimilarIdsById("1");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}