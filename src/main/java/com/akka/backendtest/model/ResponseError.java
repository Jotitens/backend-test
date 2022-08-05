package com.akka.backendtest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.List;


@AllArgsConstructor
@Builder
@Data
@ApiModel(value = "ResponseError", description = "Error response")
public class ResponseError {

    @ApiModelProperty(value = "errors")
    private List<ResponseDataError> errors;

}
