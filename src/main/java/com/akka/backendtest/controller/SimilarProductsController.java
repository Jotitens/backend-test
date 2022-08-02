package com.akka.backendtest.controller;

import com.akka.backendtest.controller.model.SimilarProducts;
import com.akka.backendtest.model.ResponseError;
import com.akka.backendtest.service.SimilarProductService;
import com.akka.backendtest.utils.Constants;
import com.akka.backendtest.utils.ConstantsPath;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 * Similar Products interface.
 * </p>
 *
 * @author Juan Jose Gomez
 * @version 0.0.1
 */
@Validated
@Api(value = "Similar Products Endpoints", tags = {"SimilarProducts"})
@RequestMapping(value = ConstantsPath.CONST_BASE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public interface SimilarProductsController {

        @ApiResponses(value = {
                @ApiResponse(code = 200, message = "OK", response = SimilarProducts.class),
                @ApiResponse(code = 404, message = "Not Found", response = ResponseError.class),
                @ApiResponse(code = 400, message = "Bad Request", response = ResponseError.class)
        })
        @ResponseStatus(value = HttpStatus.OK)
        @GetMapping(value = ConstantsPath.CONST_GET_PRODUCTS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
        ResponseEntity<SimilarProducts> getSimilarProducts(@PathVariable @Valid @NotBlank String productId) throws Exception;


}
