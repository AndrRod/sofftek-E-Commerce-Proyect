package com.LicuadoraProyectoEcommerce.dto.manager;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor @Builder @NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BaseProductDtoComplete {
    @Schema(name = "id", example = "1")
    private Long id;
    @Schema(name = "name", example = "zapatilla de futbol")
    private String name;
    @Schema(name = "price", example = "12000")
    @JsonProperty("price")
    private Double price;
    @Schema(name = "days to manufacture", example = "12")
    @JsonProperty("days to manufacture")
    private Integer daysToManufacture;
    private String manager;
    @JsonProperty("enabled areas")
    private List<EnabledAreaCompleteDto> enabledAreas;
    @Schema(name = "creation date", example = "2022-05-12")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("creation date")
    private LocalDate creationDate;
    @Schema(name = "update date", example = "2022-05-12 22:05:33")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonProperty("update date")
    private LocalDateTime updateDate;
    @Schema(name = "manager email of the last update", example = "andresManager@gmail.com")
    @JsonProperty("manager email of the last update")
    private String managerLastUpdate;
}
