package CuentasClaras.CuentasClaras.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import CuentasClaras.CuentasClaras.Interfaces.IPayment;
import CuentasClaras.CuentasClaras.Modelos.Payment;

@RestController
public class PaymentController {
	
	@Autowired 
	private IPayment service;
	
	public boolean updatePayment(int id) {
		Payment p = service.findById(id).orElse(null);
		if(p == null) return false;
		p.setAccredited(true);
		service.save(p);
		return true;
	}

}
