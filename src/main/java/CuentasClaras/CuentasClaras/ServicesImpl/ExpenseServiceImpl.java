package CuentasClaras.CuentasClaras.ServicesImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import CuentasClaras.CuentasClaras.Interfaces.IDivision;
import CuentasClaras.CuentasClaras.Interfaces.IExpense;
import CuentasClaras.CuentasClaras.Interfaces.IMultipleUser;
import CuentasClaras.CuentasClaras.Interfaces.IPayment;
import CuentasClaras.CuentasClaras.Modelos.Expense;
import CuentasClaras.CuentasClaras.Modelos.Payment;
import CuentasClaras.CuentasClaras.Services.ExpenseService;

public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private IExpense expenseService;
	@Autowired
	private IPayment paymentService;
	@Autowired
	private IDivision divisionService;
	@Autowired
	private IMultipleUser multipleUserService;

	public ResponseEntity<List<Expense>> findAll() {
		List<Expense> listExpenses = (List<Expense>) expenseService.findAll();
		if (listExpenses.size() == 0) {
			return new ResponseEntity<List<Expense>>(HttpStatus.NOT_FOUND);
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
		if (listExpenses.size() == 0) {
			return new ResponseEntity<List<Expense>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Expense>>(listExpenses, HttpStatus.OK);
	}

	public ResponseEntity<Expense> findById(int id) {
		Expense expense = expenseService.findById(id).orElse(null);
		if (expense == null)
			return new ResponseEntity<Expense>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<Expense>(expense, HttpStatus.OK);
	}

	public ResponseEntity<Expense> save(Expense expense) {
		expense.getDivisions().stream().forEach(division -> {
			Payment p = new Payment(division.getUserOwner(), division.getAmount(), false, LocalDate.now());
			paymentService.save(p);
		});
		expense.getAmountUsers().stream().forEach(MUsers -> {
			multipleUserService.save(MUsers);
		});
		expenseService.save(expense);
		Expense e = expenseService.findById(expense.getId()).orElse(null);
		if (e == null)
			return new ResponseEntity<Expense>(HttpStatus.BAD_REQUEST);

		expense.getDivisions().stream().forEach(division -> {
			divisionService.save(division);
		});
		return new ResponseEntity<Expense>(HttpStatus.OK);
	}

	public ResponseEntity<Expense> delete(int id) {
		expenseService.deleteById(id);
		Expense e = expenseService.findById(id).orElse(null);
		if (e == null)
			return new ResponseEntity<Expense>(HttpStatus.OK);

		return new ResponseEntity<Expense>(HttpStatus.BAD_REQUEST);
	}
}
