package CuentasClaras.CuentasClaras.Controllers;

import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import CuentasClaras.CuentasClaras.Interfaces.*;
import CuentasClaras.CuentasClaras.Modelos.*;

@RestController
@RequestMapping("expenses")
public class ExpenseController {
	
	@Autowired
	private IExpense expenseService;
	@Autowired
	private IPayment paymentService;
	@Autowired
	private IDivision divisionService;
	@Autowired
	private IMultipleUser multipleUserService;
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Expense>> findAll() {
		List<Expense> listExpenses = (List<Expense>) expenseService.findAll();
		if (listExpenses.size() == 0) {
			return new ResponseEntity<List<Expense>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Expense>>(listExpenses, HttpStatus.OK);
	}
	
	@GetMapping("/findByUser/{id}")
	public ResponseEntity<List<Expense>> findByUserOwner(@PathVariable int id) {
		List<Expense> listExpenses = (List<Expense>) expenseService.findByUserOwner(id);
		if (listExpenses.size() == 0) {
			return new ResponseEntity<List<Expense>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Expense>>(listExpenses, HttpStatus.OK);
	}
	
	@GetMapping("/findByGroup/{id}")
	public ResponseEntity<List<Expense>> findByGroupOwner(@PathVariable int id) {
		List<Expense> listExpenses = (List<Expense>) expenseService.findByGroupOwner(id);
		if (listExpenses.size() == 0) {
			return new ResponseEntity<List<Expense>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Expense>>(listExpenses, HttpStatus.OK);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<Expense> findById(@PathVariable int id) {
		Expense expense = expenseService.findById(id).orElse(null);
		if (expense == null)
			return new ResponseEntity<Expense>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<Expense>(expense, HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Expense> save(@RequestBody Expense expense) {
		expense.getDivisions().stream().forEach(division -> {
		    Payment p = new Payment(division.getUserOwner(), division.getAmount(), false, LocalDate.now());
		    paymentService.save(p);
		    divisionService.save(division);
		});
		expense.getAmountUsers().stream().forEach(MUsers -> {
		    multipleUserService.save(MUsers);
		});
		expenseService.save(expense);
		Expense e = expenseService.findById(expense.getId()).orElse(null);
		if (e == null)
			return new ResponseEntity<Expense>(HttpStatus.BAD_REQUEST);

		return new ResponseEntity<Expense>(HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Expense> delete(@PathVariable int id) {
		expenseService.deleteById(id);
		Expense e = expenseService.findById(id).orElse(null);
		if (e == null)
			return new ResponseEntity<Expense>(HttpStatus.OK);

		return new ResponseEntity<Expense>(HttpStatus.BAD_REQUEST);
	}

}
