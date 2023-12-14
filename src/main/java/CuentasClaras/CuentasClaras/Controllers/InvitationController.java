package CuentasClaras.CuentasClaras.Controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import CuentasClaras.CuentasClaras.Services.InvitationService;

@RestController
@RequestMapping("invitation")
public class InvitationController {
	
	@Autowired
	private InvitationService invitationService;


	@PostMapping("/accept/{id}")
	public ResponseEntity<?> accept(@PathVariable int id) {
		return invitationService.accept(id);
	}
	
	@DeleteMapping("/reject/{id}")
	public ResponseEntity<?> reject(@PathVariable int id) {
		return invitationService.reject(id);
	}
}
