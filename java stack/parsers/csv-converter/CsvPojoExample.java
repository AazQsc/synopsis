package com.home.databinding.csv;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"ID", "NAME"})
public class CsvPojoExample {

    @NotBlank
    @JsonProperty(value = "ID")
    private String id;

    @NotBlank
    @JsonProperty(value = "NAME")
    private String name;
}
