package CuentasClaras.CuentasClaras.Controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import CuentasClaras.CuentasClaras.Modelos.*;
import CuentasClaras.CuentasClaras.Request.EditExpenseDTO;
import CuentasClaras.CuentasClaras.Services.ExpenseService;

@RestController
@RequestMapping("expenses")
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		return this.expenseService.findAll();
	}
	
	@GetMapping("/findByUser/{id}")
	public ResponseEntity<List<Expense>> findByUserOwner(@PathVariable int id) {
		return this.expenseService.findByUserOwner(id);

	}
	
	@GetMapping("/findByGroup/{id}")
	public ResponseEntity<List<Expense>> findByGroupOwner(@PathVariable int id) {
		return this.expenseService.findByGroupOwner(id);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<Expense> findById(@PathVariable int id) {
		return this.expenseService.findById(id);
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Expense expense) {
		return this.expenseService.save(expense);
	}
	
	@PostMapping("/edit")
	public ResponseEntity<?> edit(@RequestBody EditExpenseDTO expenseDTO) {
		Expense expense=new Expense();
		
		expense.setId(expenseDTO.id());
		expense.setImg(expenseDTO.img());
		expense.setRecurrent(expense.isRecurrent());
		expense.setRecurrency(expenseDTO.recurrency());
		expense.setCantRecurrency(expenseDTO.cantRecurrency());
		
		return this.expenseService.edit(expense,expenseDTO.categoryName());
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Expense> delete(@PathVariable int id) {
		return this.expenseService.delete(id);
	}

}
