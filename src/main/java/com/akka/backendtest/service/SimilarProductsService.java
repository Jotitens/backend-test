package com.akka.backendtest.service;

import com.akka.backendtest.controller.model.SimilarProducts;

public interface SimilarProductsService {

    SimilarProducts getSimilarProducts(String productId) throws Exception;

}
