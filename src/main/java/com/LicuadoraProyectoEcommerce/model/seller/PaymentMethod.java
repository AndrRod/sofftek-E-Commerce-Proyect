package com.LicuadoraProyectoEcommerce.model.seller;

import io.swagger.v3.oas.annotations.media.Schema;

public enum PaymentMethod {
    @Schema(example = "CREDIT_CARD")
    CREDIT_CARD,
    @Schema(example = "DEBIT_CARD")
    DEBIT_CARD,
    @Schema(example = "CASH")
    CASH,
    @Schema(example = "TRANSFER")
    TRANSFER
}
