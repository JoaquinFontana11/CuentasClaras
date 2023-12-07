package CuentasClaras.CuentasClaras.Controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import CuentasClaras.CuentasClaras.Interfaces.*;
import CuentasClaras.CuentasClaras.Modelos.*;

@RestController
@RequestMapping("invitation")
public class InvitationController {
	
	@Autowired
	private IInvitation invitationService;
	@Autowired
	private IUser userService;
	@Autowired
	private IFriendship friendService;
	@Autowired
	private GroupController groupService;

	@PostMapping("/accept/{id}")
	public ResponseEntity<?> accept(@PathVariable int id) {
		Invitation invitation = invitationService.findById(id).orElse(null);
		if (invitation == null)
			return new ResponseEntity<String>("invitacion no existente",HttpStatus.NOT_FOUND);
		
		if(invitation.isGroup()) {
		Group group = groupService.findByName(invitation.getInviteName()).get();
		invitation.getUser().addGroups(group);
		
		} else {
			User user = (User) userService.findByuserName(invitation.getInviteName()).get();
			Friendship friend = new Friendship(invitation.getUser(),user);
			friendService.save(friend);
			friend = new Friendship(user,invitation.getUser());
			friendService.save(friend);
		}
		userService.save(invitation.getUser());
		return new ResponseEntity<String>("Invitacion aceptada",HttpStatus.OK);
		
	}
	
	@DeleteMapping("/reject/{id}")
	public ResponseEntity<?> reject(@PathVariable int id) {
		invitationService.deleteById(id);
		Invitation i = invitationService.findById(id).orElse(null);
		if (i == null)
			return new ResponseEntity<String>("Invitacion rechazada",HttpStatus.OK);

		return new ResponseEntity<Expense>(HttpStatus.BAD_REQUEST);
	}
}
