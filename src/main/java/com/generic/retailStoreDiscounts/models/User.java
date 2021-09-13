package com.generic.retailStoreDiscounts.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public class User {

    @NotNull(message = "type field is mandatory.")
    @JsonProperty("type")
	private UserType type;
	private LocalDate registerDate;
}
