package CuentasClaras.CuentasClaras.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
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
}
