package com.generic.theRetailStoreDiscounts.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public class User {

	private UserType type;
	private LocalDate registerDate;
}
