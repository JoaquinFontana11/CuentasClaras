package CuentasClaras.CuentasClaras.Request;

import java.util.List;

import CuentasClaras.CuentasClaras.Modelos.Division;

public record EditExpenseDTO(
		int id,
		String img,
		boolean isRecurrent,
		String recurrency,
		int cantRecurrency,
		String categoryName
		) {}
