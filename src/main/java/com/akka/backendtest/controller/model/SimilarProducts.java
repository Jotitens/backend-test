package com.akka.backendtest.controller.model;


import com.akka.backendtest.existingApis.model.ProductDetail;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimilarProducts {

    @JsonProperty("similar_products")
    private List<ProductDetail> similarProducts;
}


