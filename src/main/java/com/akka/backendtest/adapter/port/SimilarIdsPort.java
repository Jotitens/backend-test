package com.akka.backendtest.adapter.port;

import com.akka.backendtest.existingApis.model.ProductDetail;

import java.util.Set;

public interface SimilarIdsPort {
    public Set<String> getProductSimilarIds(String productId) throws Exception;
    public ProductDetail getProductDetail(String productId) throws Exception;
}
