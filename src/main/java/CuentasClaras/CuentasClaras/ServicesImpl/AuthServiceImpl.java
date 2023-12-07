package CuentasClaras.CuentasClaras.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import CuentasClaras.CuentasClaras.Interfaces.IUser;
import CuentasClaras.CuentasClaras.Modelos.User;
import CuentasClaras.CuentasClaras.Request.AuthDto;
import CuentasClaras.CuentasClaras.Services.AuthService;

public class AuthServiceImpl implements AuthService {

	@Autowired
	private IUser userService;
	
	public ResponseEntity<String> login(String username, String password) {
		User user = (User) userService.findByuserName(username).orElse(null);

		if (user == null)
			return new ResponseEntity<String>("Loggin sin exito", HttpStatus.NOT_FOUND);

		if (!user.getPassword().equals(password))
			return new ResponseEntity<String>("Contraseña Incorrecta", HttpStatus.BAD_REQUEST);

		return new ResponseEntity<String>("Usuario logeado con exito", HttpStatus.OK);
	}

}
