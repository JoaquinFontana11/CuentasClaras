package CuentasClaras.CuentasClaras.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import CuentasClaras.CuentasClaras.Interfaces.IUser;
import CuentasClaras.CuentasClaras.Modelos.User;
import CuentasClaras.CuentasClaras.Request.AuthDto;

@RestController
@RequestMapping("auth")
public class AuthController {
	@Autowired
	private IUser service;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody AuthDto authDto){
		User user = (User) service.findByuserName(authDto.getUserName()).orElse(null);
		
		if (user == null)
			return new ResponseEntity<String>("Loggin sin exito", HttpStatus.NOT_FOUND);
		
		if (!user.getPassword().equals(authDto.getPassword()))
			return new ResponseEntity<String>("Contraseña Incorrecta", HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<String>("Usuario logeado con exito", HttpStatus.OK);
	}

}
