package com.akka.backendtest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ResponseError", description = "Error response")
public class ResponseError {
    @ApiModelProperty(value = "errors")
    private List<ResponseDataError> errors;
}
