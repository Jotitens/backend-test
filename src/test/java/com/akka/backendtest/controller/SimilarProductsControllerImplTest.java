package com.akka.backendtest.controller;

import com.akka.backendtest.controller.model.SimilarProducts;
import com.akka.backendtest.existingApis.model.ProductDetail;
import com.akka.backendtest.testUtils.VariableSource;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SimilarProductsControllerImplTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    private static final int OK = 200;
    private static final int BAD_REQUEST = 400;
    private static final int NOT_FOUND = 404;
    private static final int METHOD_NOT_ALLOWED = 405;
    private static final int INTERNAL_SERVER_ERROR = 500;

    public static Stream<Arguments> getPathArguments = Stream.of(
            Arguments.of("1", OK, List.of("4","3","2")),
            Arguments.of("2", OK, List.of("3")),
            Arguments.of("3", NOT_FOUND, Collections.EMPTY_LIST),
            Arguments.of("4", OK, List.of("1","2")),
            Arguments.of("5", INTERNAL_SERVER_ERROR, null),
            Arguments.of("6", NOT_FOUND, null),
            Arguments.of("A", BAD_REQUEST, null)
    );

    @ParameterizedTest
    @VariableSource("getPathArguments")
    public void getSimilarProducts(String productId, int expectedCode, List<String> idsExpected) throws Exception {
        if(expectedCode != OK){
            mvc.perform(MockMvcRequestBuilders.get("/product/"+productId+"/similar"))
                    .andExpect(MockMvcResultMatchers.status().is(expectedCode));
        } else {
            String result = mvc.perform(MockMvcRequestBuilders.get("/product/"+productId+"/similar"))
                    .andExpectAll(MockMvcResultMatchers.status().is(expectedCode))
                    .andReturn().getResponse().getContentAsString();

            SimilarProducts similarProducts = objectMapper.readValue(result, SimilarProducts.class);

            assertEquals(similarProducts.getSimilarProducts().stream()
                    .map(ProductDetail::getId).sorted().collect(Collectors.toList()),
                    idsExpected.stream().sorted().collect(Collectors.toList()));
        }
    }

    public static Stream<Arguments> getMethodNotValidArguments = Stream.of(
            Arguments.of("1", METHOD_NOT_ALLOWED, null)
    );

    @ParameterizedTest
    @VariableSource("getMethodNotValidArguments")
    public void methodNotValidTest(String productId, int expectedCode, List<String> idsExpected) throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/product/"+productId+"/similar"))
                .andExpect(MockMvcResultMatchers.status().is(expectedCode));

    }



}
