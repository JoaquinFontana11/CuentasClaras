package CuentasClaras.CuentasClaras.Services;

import org.springframework.http.ResponseEntity;
import CuentasClaras.CuentasClaras.Modelos.Group;
import CuentasClaras.CuentasClaras.Modelos.Invitation;

public interface GroupService {
	
	public ResponseEntity<?> save(Group group);

    public ResponseEntity<?> addMember(int id, Invitation invitation);
    
    public ResponseEntity<?> edit (Group group);
    
    public ResponseEntity<?> findByname(String name);
    
}