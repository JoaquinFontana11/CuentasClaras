package CuentasClaras.CuentasClaras.Controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import CuentasClaras.CuentasClaras.Modelos.*;
import CuentasClaras.CuentasClaras.Services.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserService userService;
	

	@GetMapping("/findAll")
	public ResponseEntity<List<User>> findAll() {
		return userService.findAll();
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<User> findById(@PathVariable int id) {
		return userService.findById(id);
	}
	
	@GetMapping("/findByEmail/{email}")
	public ResponseEntity<User> findByEmail(@PathVariable String email) {
		return userService.findByEmail(email);
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody User user) {
		return userService.save(user);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		return userService.delete(id);
	}


    @PostMapping("/addFriend/{idUserToAdd}")
	public ResponseEntity<?> addFriend(@PathVariable int idUserToAdd, @RequestBody int idUserWhoAdd){
		return userService.addFriend(idUserToAdd, idUserWhoAdd);
	}
    
    @PostMapping("/pay/{paymentId}")
    public ResponseEntity<?> pay(@PathVariable int paymentId) {
    	return userService.pay(paymentId);
    	
    }
    
    
}
