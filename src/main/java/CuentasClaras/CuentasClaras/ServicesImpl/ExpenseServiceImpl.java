package CuentasClaras.CuentasClaras.ServicesImpl;

import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import CuentasClaras.CuentasClaras.Interfaces.ICategory;
import CuentasClaras.CuentasClaras.Interfaces.IDivision;
import CuentasClaras.CuentasClaras.Interfaces.IExpense;
import CuentasClaras.CuentasClaras.Interfaces.IMultipleUser;
import CuentasClaras.CuentasClaras.Interfaces.IPayment;
import CuentasClaras.CuentasClaras.Modelos.Expense;
import CuentasClaras.CuentasClaras.Modelos.Payment;
import CuentasClaras.CuentasClaras.Services.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private IExpense expenseService;
	@Autowired
	private IPayment paymentService;
	@Autowired
	private IDivision divisionService;
	@Autowired
	private IMultipleUser multipleUserService;
	@Autowired
	private ICategory categoryService;

	public ResponseEntity<?> findAll() {
		Iterable<Expense> iterable = expenseService.findAll();
		List<Expense> listExpenses = new ArrayList<Expense>();
		iterable.forEach(listExpenses::add);
		if (listExpenses.size() == 0) {
			return new ResponseEntity<String>("No hay gastos cargados", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Expense>>(listExpenses, HttpStatus.OK);
	}

	public ResponseEntity<List<Expense>> findByUserOwner(int id) {
		List<Expense> listExpenses = (List<Expense>) expenseService.findByUserOwner(id);
		if (listExpenses.size() == 0) {
			return new ResponseEntity<List<Expense>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Expense>>(listExpenses, HttpStatus.OK);
	}

	public ResponseEntity<List<Expense>> findByGroupOwner(int id) {
		List<Expense> listExpenses = (List<Expense>) expenseService.findByGroupOwner(id);

		return new ResponseEntity<List<Expense>>(listExpenses, HttpStatus.OK);
	}

	public ResponseEntity<Expense> findById(int id) {
		Expense expense = expenseService.findById(id).orElse(null);
		if (expense == null)
			return new ResponseEntity<Expense>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<Expense>(expense, HttpStatus.OK);
	}

	public ResponseEntity<?> save(Expense expense) {

		expense.getDivisions().stream().forEach(division -> {
			Payment p = new Payment(division.getUserOwner(), division.getAmount(), false, LocalDate.now());
			paymentService.save(p);
		});

		if ((categoryService.findByname(expense.getCategory().getName()).orElse(null)) == null) {
			return new ResponseEntity<String>("Category not found", HttpStatus.BAD_REQUEST);
		} else {
			expense.setCategory(categoryService.findByname(expense.getCategory().getName()).get());
		}

		expenseService.save(expense);

		Expense e = expenseService.findById(expense.getId()).orElse(null);
		if (e == null)
			return new ResponseEntity<Expense>(HttpStatus.BAD_REQUEST);

		expense.getAmountUsers().stream().forEach(MUsers -> {
			MUsers.setExpense(expense);
			multipleUserService.save(MUsers);
		});

		expense.getDivisions().stream().forEach(division -> {
			division.setExpense(expense);
			divisionService.save(division);
		});

		return new ResponseEntity<Expense>(HttpStatus.OK);
	}

	public ResponseEntity<?> edit(Expense expense) {
		Expense expenseSearched = expenseService.findById(expense.getId()).orElse(null);
		if (expenseSearched == null)
			return new ResponseEntity<String>("Expense Not Found", HttpStatus.BAD_REQUEST);

		if ((categoryService.findByname(expense.getCategory().getName()).orElse(null)) == null)
			return new ResponseEntity<String>("Category not found", HttpStatus.BAD_REQUEST);
		
		expense.setCategory(categoryService.findByname(expense.getCategory().getName()).get());

		this.expenseService.save(expense);
		expenseSearched = expenseService.findById(expense.getId()).orElse(null);
		return new ResponseEntity<Expense>(expenseSearched, HttpStatus.OK);
	}

	public ResponseEntity<Expense> delete(int id) {
		expenseService.deleteById(id);
		Expense e = expenseService.findById(id).orElse(null);
		if (e == null)
			return new ResponseEntity<Expense>(HttpStatus.OK);

		return new ResponseEntity<Expense>(HttpStatus.BAD_REQUEST);
	}
}
