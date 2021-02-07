package com.compasso.interview.helper;

import java.time.LocalDate;
import java.time.Period;

public final class DataHelper {

	public static Long calcularIdade(LocalDate dataNascimento) {

		return Long.valueOf(Period.between(dataNascimento, LocalDate.now()).getYears());
	}

}
