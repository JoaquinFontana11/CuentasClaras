package CuentasClaras.CuentasClaras.Request;

import CuentasClaras.CuentasClaras.Modelos.Category;

public record EditGroupDTO(
		int id,
		String name,
		String categoryName
		) {}
