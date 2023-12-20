package CuentasClaras.CuentasClaras.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import CuentasClaras.CuentasClaras.Interfaces.IUser;
import CuentasClaras.CuentasClaras.Modelos.User;
import CuentasClaras.CuentasClaras.Services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private IUser userService;
	
	public ResponseEntity<?> login(String username, String password) {
		User user = (User) userService.findByuserName(username).orElse(null);

		if (user == null)
			return new ResponseEntity<String>("Loggin sin exito", HttpStatus.NOT_FOUND);

		if (!user.getPassword().equals(password))
			return new ResponseEntity<String>("Contraseña Incorrecta", HttpStatus.BAD_REQUEST);

		return new ResponseEntity<Number>(user.getId(), HttpStatus.OK);
	}

}
