package com.akka.backendtest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
@ApiModel(value = "ResponseDataError", description = "Error date response")
public class ResponseDataError {

    @ApiModelProperty(value = "code", example = "404")
    @JsonProperty("code")
    private String code;
    @ApiModelProperty(value = "description", example = "Any description")
    @JsonProperty("description")
    private String description;
}
