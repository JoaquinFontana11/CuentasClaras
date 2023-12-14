package CuentasClaras.CuentasClaras.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import CuentasClaras.CuentasClaras.Services.PaymentService;

@RestController
public class PaymentController {
	
	@Autowired 
	private PaymentService paymentService;
	
	public boolean updatePayment(int id) {
		return this.paymentService.updatePayment(id);
	}

}
