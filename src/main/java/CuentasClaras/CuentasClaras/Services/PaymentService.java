package CuentasClaras.CuentasClaras.Services;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import CuentasClaras.CuentasClaras.Modelos.Payment;

public interface PaymentService {
	
	public boolean updatePayment(int id);
	
	public Payment findByDebtorANDExpense(int debtor_id,int expense_id);

}
