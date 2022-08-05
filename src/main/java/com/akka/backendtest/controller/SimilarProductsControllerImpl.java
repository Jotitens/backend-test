package com.akka.backendtest.controller;

import com.akka.backendtest.controller.model.SimilarProducts;
import com.akka.backendtest.service.SimilarProductsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimilarProductsControllerImpl implements SimilarProductsController {

    @Autowired
    private SimilarProductsServiceImpl similarProductsService;

    @Override
    public ResponseEntity<SimilarProducts> getSimilarProducts(String productId) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(similarProductsService.getSimilarProducts(productId));
    }
}
