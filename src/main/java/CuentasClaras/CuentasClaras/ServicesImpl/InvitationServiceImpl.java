package CuentasClaras.CuentasClaras.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import CuentasClaras.CuentasClaras.Controllers.GroupController;
import CuentasClaras.CuentasClaras.Interfaces.IFriendship;
import CuentasClaras.CuentasClaras.Interfaces.IInvitation;
import CuentasClaras.CuentasClaras.Interfaces.IUser;
import CuentasClaras.CuentasClaras.Modelos.Expense;
import CuentasClaras.CuentasClaras.Modelos.Friendship;
import CuentasClaras.CuentasClaras.Modelos.Group;
import CuentasClaras.CuentasClaras.Modelos.Invitation;
import CuentasClaras.CuentasClaras.Modelos.User;
import CuentasClaras.CuentasClaras.Services.InvitationService;

@Service
public class InvitationServiceImpl implements InvitationService {
	
	@Autowired
	private IInvitation invitationService;
	@Autowired
	private IUser userService;
	@Autowired
	private IFriendship friendService;
	@Autowired
	private GroupController groupService;

	
	public ResponseEntity<?> accept(int id) {
		Invitation invitation = invitationService.findById(id).orElse(null);
		if (invitation == null)
			return new ResponseEntity<String>("invitacion no existente",HttpStatus.NOT_FOUND);
		
		if(invitation.isGroup()) {
		Group group = (Group) groupService.findByName(invitation.getInviteName()).getBody();
		invitation.getUser().addGroups(group);
		
		} else {
			User user = (User) userService.findByuserName(invitation.getInviteName()).get();
			Friendship friend = new Friendship(invitation.getUser(),user);
			friendService.save(friend);
			friend = new Friendship(user,invitation.getUser());
			friendService.save(friend);
		}
		userService.save(invitation.getUser());
		invitation.setState(true);
		invitationService.save(invitation);
		return new ResponseEntity<String>("Invitacion aceptada",HttpStatus.OK);
		
	}
	
	public ResponseEntity<?> reject(int id) {
		invitationService.deleteById(id);
		Invitation i = invitationService.findById(id).orElse(null);
		if (i == null)
			return new ResponseEntity<String>("Invitacion rechazada",HttpStatus.OK);

		return new ResponseEntity<Expense>(HttpStatus.BAD_REQUEST);
	}
}
