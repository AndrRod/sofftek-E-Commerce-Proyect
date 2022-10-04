package com.LicuadoraProyectoEcommerce.dto.manager;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BaseProductDtoComplete {
    private Long id;
    private String name;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("days to manufacture")
    private Integer daysToManufacture;
    private String manager;
    @JsonProperty("enabled areas")
    private List<EnabledAreaCompleteDto> enabledAreas;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("creation date")
    private LocalDate creationDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonProperty("update date")
    private LocalDateTime updateDate;
    @JsonProperty("manager email of the last update")
    private String managerLastUpdate;
}
