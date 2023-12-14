package CuentasClaras.CuentasClaras.Services;

import org.springframework.http.ResponseEntity;


public interface InvitationService {

public ResponseEntity<?> accept(int id);

public ResponseEntity<?> reject(int id);

}
