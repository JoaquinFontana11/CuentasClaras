package CuentasClaras.CuentasClaras.Services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import CuentasClaras.CuentasClaras.Modelos.Expense;

public interface ExpenseService {
	public ResponseEntity<?> findAll();
	
	public ResponseEntity<List<Expense>> findByUserOwner(int id);
	
	public ResponseEntity<List<Expense>> findByGroupOwner(int id);
	
	public ResponseEntity<Expense> findById(int id);
	
	public ResponseEntity<?> save(Expense expense);
	
	public ResponseEntity<?> edit(Expense expense,String categoryName);
	
	public ResponseEntity<Expense> delete(int id);
}
