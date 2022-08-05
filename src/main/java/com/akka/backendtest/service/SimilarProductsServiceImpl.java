package com.akka.backendtest.service;

import com.akka.backendtest.adapter.ProductApiAdapter;
import com.akka.backendtest.controller.model.SimilarProducts;
import com.akka.backendtest.exception.NotFoundException;
import com.akka.backendtest.existingApis.model.ProductDetail;
import com.akka.backendtest.utils.UtilsLog;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.akka.backendtest.utils.Constants.LEVEL_DEBUG;

@Service
@AllArgsConstructor
public class SimilarProductsServiceImpl implements SimilarProductsService {

    @Autowired
    private ProductApiAdapter productApiAdapter;

    public SimilarProducts getSimilarProducts(String productId) throws Exception{
        UtilsLog.logDash();
        UtilsLog.customLog(LEVEL_DEBUG,"Starting process - Initial Request: " + productId);
        List<ProductDetail> products = getSimilarIdsById(productId).stream()
                .map(this::getProductDetailsBySimilarId)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if(products.isEmpty()){
            throw new NotFoundException("No products found");
        }
        UtilsLog.customLog(LEVEL_DEBUG,"Ending process - Final response: ");
        products.forEach(product -> UtilsLog.customLog(LEVEL_DEBUG, product.toString()));
        UtilsLog.logDash();
        return SimilarProducts.builder()
                .similarProducts(products)
                .build();
    }

    private Set<String> getSimilarIdsById(String productId) throws Exception {
        return productApiAdapter.getProductSimilarIds(productId);
    }


    private ProductDetail getProductDetailsBySimilarId(String similarProductId) {
        return productApiAdapter.getProductDetail(similarProductId);
    }
}
