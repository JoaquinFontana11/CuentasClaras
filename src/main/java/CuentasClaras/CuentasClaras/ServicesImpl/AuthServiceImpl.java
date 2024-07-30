package CuentasClaras.CuentasClaras.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import CuentasClaras.CuentasClaras.Interfaces.IUser;
import CuentasClaras.CuentasClaras.Modelos.ApiError;
import CuentasClaras.CuentasClaras.Modelos.User;
import CuentasClaras.CuentasClaras.Services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private IUser userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public ResponseEntity<?> login(String username, String password) {
		User user = (User) userService.findByuserName(username).orElse(null);
		
		if (user == null)
			return new ResponseEntity<>(new ApiError("User not found"), HttpStatus.BAD_REQUEST);

		if (!passwordEncoder.matches(password, user.getPassword()))
			return new ResponseEntity<>(new ApiError("Contraseña Incorrecta"), HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(user.getId(), HttpStatus.OK);
	}

}
