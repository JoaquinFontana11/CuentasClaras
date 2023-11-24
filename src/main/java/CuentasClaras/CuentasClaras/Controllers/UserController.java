package CuentasClaras.CuentasClaras.Controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import CuentasClaras.CuentasClaras.Interfaces.IUser;
import CuentasClaras.CuentasClaras.Modelos.*;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private IUser service;
	
	@Autowired
	private PaymentController paymentController;

	@GetMapping("/findAll")
	public ResponseEntity<List<User>> findAll() {
		List<User> listUser = (List<User>) service.findAll();
		if (listUser.size() == 0) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(listUser, HttpStatus.OK);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<User> findById(@PathVariable int id) {
		User user = service.findById(id).orElse(null);
		if (user == null)
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity save(@RequestBody User user) {
		service.save(user);
		User u = service.findById(user.getId()).orElse(null);
		if (u == null)
			return new ResponseEntity(HttpStatus.BAD_REQUEST);

		return new ResponseEntity(HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity delete(@PathVariable int id) {
		service.deleteById(id);
		User u = service.findById(id).orElse(null);
		if (u == null)
			return new ResponseEntity(HttpStatus.OK);

		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}


    @PostMapping("/addFriend/{id}")
	public ResponseEntity<?> addFriend(@PathVariable int idUserToAdd, @RequestBody int idUserWhoAdd){
		User userToAdd = service.findById(idUserToAdd).orElse(null);
		User userWhoAdd = service.findById(idUserWhoAdd).orElse(null);

		if ((userWhoAdd != null)&&(userToAdd != null)){
			Invitation invitation = new Invitation(userWhoAdd.getUsername(),false,userToAdd);
			userToAdd.addInvitation(invitation);
			service.save(userToAdd);
			return new ResponseEntity<String>("Invitation sended",HttpStatus.OK);
		}else{
			return new ResponseEntity<String>("User not found", HttpStatus.BAD_REQUEST);
		}
	}
    
    @PostMapping("/pay/{id}")
    public ResponseEntity<?> pay(@PathVariable int paymentId) {
    	boolean res = paymentController.updatePayment(paymentId);
    	if (res) {
    		return new ResponseEntity<String>("Successful payment",HttpStatus.OK);
    	} else {
    		return new ResponseEntity<String>("Payment not found", HttpStatus.BAD_REQUEST);
    	}
    	
    }
}
