package CuentasClaras.CuentasClaras.ServicesImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import CuentasClaras.CuentasClaras.Interfaces.IPayment;
import CuentasClaras.CuentasClaras.Modelos.Payment;
import CuentasClaras.CuentasClaras.Services.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	
	@Autowired 
	private IPayment paymentService;
	
	public boolean updatePayment(int id) {
		Payment p = paymentService.findById(id).orElse(null);
		if(p == null) return false;
		p.setAccredited(true);
		paymentService.save(p);
		return true;
	}
	
	public Payment findByDebtorANDExpense(int debtor_id,int expense_id){
		Optional<Payment> p = paymentService.findByDebtorANDExpense(debtor_id, expense_id);
		if (p.isEmpty()) 
			return null;
		
		return p.get();
	}
}
