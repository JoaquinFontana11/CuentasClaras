package CuentasClaras.CuentasClaras.Services;

import org.springframework.http.ResponseEntity;


import CuentasClaras.CuentasClaras.Modelos.Category;

public interface CategoryService {

	public ResponseEntity<?> findAll();
	
	public ResponseEntity<?> save(Category category);
	
	public ResponseEntity<?> find(int id);
}
