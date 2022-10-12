package com.LicuadoraProyectoEcommerce.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class UserRefreshTokenResponse {
    @Schema(name = "message", example = "the user victor@gmail.com refresh the token successfully")
    private String message;
    @Schema(name = "access_token", example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2aWN0b3JAZ21haWwuY29tIiwicm9sZSI6WyJST0xFX1NFTExFUiJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXV0aC9yZWZyZXNoIiwiZXhwIjoxNjY1NTM2OTAxfQ.SoVXxciAoYtGhXTSg8tpi6nk1EhZn7ynff8Vr7_a0PY")
    @JsonProperty("access_token")
    private String accessToken;
    @Schema(name= "update_token", example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2aWN0b3JAZ21haWwuY29tIiwicm9sZSI6WyJST0xFX1NFTExFUiJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXV0aC9yZWZyZXNoIiwiZXhwIjoxNjY1NTM2OTAxfQ.SoVXxciAoYtGhXTSg8tpi6nk1EhZn7ynff8Vr7_a0PY")
    @JsonProperty("update_token")
    private String updateToken;
}
