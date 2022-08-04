package com.akka.backendtest.model;

import antlr.build.ANTLR;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "ResponseError", description = "Error response")
public class ResponseError {

    @ApiModelProperty(value = "errors")
    private List<ResponseDataError> errors;

}
