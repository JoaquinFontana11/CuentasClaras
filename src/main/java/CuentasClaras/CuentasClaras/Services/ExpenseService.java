package CuentasClaras.CuentasClaras.Services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import CuentasClaras.CuentasClaras.Modelos.Expense;

public interface ExpenseService {
	public ResponseEntity<List<Expense>> findAll();
	
	public ResponseEntity<List<Expense>> findByUserOwner(int id);
	
	public ResponseEntity<List<Expense>> findByGroupOwner(int id);
	
	public ResponseEntity<Expense> findById(int id);
	
	public ResponseEntity<Expense> save(Expense expense);
	
	public ResponseEntity<Expense> delete(int id);
}
