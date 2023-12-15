package CuentasClaras.CuentasClaras.ServicesImpl;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonView;

import CuentasClaras.CuentasClaras.Controllers.PaymentController;
import CuentasClaras.CuentasClaras.Interfaces.IUser;
import CuentasClaras.CuentasClaras.Modelos.Invitation;
import CuentasClaras.CuentasClaras.Modelos.User;
import CuentasClaras.CuentasClaras.Services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private IUser userService;

	@Autowired
	private PaymentController paymentController;

	
	public ResponseEntity<List<User>> findAll() {
		List<User> listUser = (List<User>) userService.findAll();
		if (listUser.size() == 0) {
			return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<User>>(listUser, HttpStatus.OK);
	}

	public ResponseEntity<User> findById(int id) {
		User user = userService.findById(id).orElse(null);
		if (user == null)
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	public ResponseEntity<?> save(User user) {
		try {
			userService.save(user);
			User u = userService.findById(user.getId()).orElse(null);
			if (u == null)
				return new ResponseEntity(HttpStatus.BAD_REQUEST);

			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Alguno de los parametros ingresados es incorrecto.", HttpStatus.BAD_REQUEST);

		}
	}

	public ResponseEntity<?> delete(int id) {
		userService.deleteById(id);
		User u = userService.findById(id).orElse(null);
		if (u == null)
			return new ResponseEntity<String>("User deleted", HttpStatus.OK);

		return new ResponseEntity<String>("User not found", HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<?> addFriend(int idUserToAdd, int idUserWhoAdd) {
		User userToAdd = userService.findById(idUserToAdd).orElse(null);
		User userWhoAdd = userService.findById(idUserWhoAdd).orElse(null);

		if ((userWhoAdd != null) && (userToAdd != null)) {
			Invitation invitation = new Invitation(userToAdd.getUserName(), false, userWhoAdd);
			userToAdd.addInvitation(invitation);
			userService.save(userToAdd);
			return new ResponseEntity<String>("Invitation sended", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("User not found", HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> pay(int paymentId) {
		boolean res = paymentController.updatePayment(paymentId);
		if (res) {
			return new ResponseEntity<String>("Successful payment", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Payment not found", HttpStatus.BAD_REQUEST);
		}

	}

}
