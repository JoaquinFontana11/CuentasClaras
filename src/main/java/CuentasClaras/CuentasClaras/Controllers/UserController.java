package CuentasClaras.CuentasClaras.Controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import CuentasClaras.CuentasClaras.Interfaces.IUser;
import CuentasClaras.CuentasClaras.Modelos.User;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private IUser service;

	@GetMapping("/findAll")
	public List<User> findAll() {
		return (List<User>)service.findAll();
	}
	
	@GetMapping("/find/{id}")
	public Optional<User> findById(@PathVariable int id) {
		return (Optional<User>)service.findById(id);
	}
	
	@PostMapping("/save")
	public void save(@RequestBody User user) {
		service.save(user);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable int id) {
		service.deleteById(id);
	}
}
