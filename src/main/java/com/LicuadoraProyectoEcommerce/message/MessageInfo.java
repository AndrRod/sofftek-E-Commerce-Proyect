package com.LicuadoraProyectoEcommerce.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageInfo {
    @JsonProperty("message")
    private String message;
    @JsonProperty("status_code")
    private Integer statusCode;
    @JsonProperty("path")
    private String path;
}