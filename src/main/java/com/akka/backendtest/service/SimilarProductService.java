package com.akka.backendtest.service;

import com.akka.backendtest.adapter.ProductApiAdapter;
import com.akka.backendtest.controller.model.SimilarProducts;
import com.akka.backendtest.existingApis.model.ProductDetail;
import com.akka.backendtest.utils.UtilsLog;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.net.http.HttpClient;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.akka.backendtest.utils.Constants.LEVEL_DEBUG;

@Service
@AllArgsConstructor
public class SimilarProductService {

    @Autowired
    private ProductApiAdapter productApiAdapter;

    public SimilarProducts getSimilarProducts(String productId) throws Exception{

        return SimilarProducts.builder()
                .similarProducts(getSimilarIdsById(productId).stream()
                        .map(this::getProductDetailsBySimilarId)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList()))
                .build();

    }

    public Set<String> getSimilarIdsById(String productId) throws Exception {
        return productApiAdapter.getProductSimilarIds(productId);
    }


    public ProductDetail getProductDetailsBySimilarId(String similarProductId) {
        ProductDetail productDetail = productApiAdapter.getProductDetail(similarProductId);



        return productDetail;
    }
}
