package 
	CuentasClaras.CuentasClaras.Services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import CuentasClaras.CuentasClaras.Modelos.User;

public interface UserService {
	
	
	public ResponseEntity<List<User>> findAll();
	
	public ResponseEntity<User> findById(int id);
	
	public ResponseEntity save(User user);
	
	public ResponseEntity delete(int id);
	
	public ResponseEntity<?> addFriend(int idUserToAdd,  int idUserWhoAdd);
	
	public ResponseEntity<?> pay(int paymentId);

}
