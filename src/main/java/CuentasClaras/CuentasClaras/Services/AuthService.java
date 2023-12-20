package CuentasClaras.CuentasClaras.Services;

import org.springframework.http.ResponseEntity;

import CuentasClaras.CuentasClaras.Request.AuthDto;

public interface AuthService {
	
	public ResponseEntity<?> login(String userName, String password);
	
	

}
