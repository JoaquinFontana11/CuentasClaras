package CuentasClaras.CuentasClaras.Services;

import org.springframework.http.ResponseEntity;

import CuentasClaras.CuentasClaras.Request.AuthDto;

public interface AuthService {
	
	public ResponseEntity<String> login(AuthDto authDto);

}
