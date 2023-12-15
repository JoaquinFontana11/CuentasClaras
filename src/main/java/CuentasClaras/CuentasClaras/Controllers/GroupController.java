package CuentasClaras.CuentasClaras.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;

import CuentasClaras.CuentasClaras.Modelos.Group;
import CuentasClaras.CuentasClaras.Modelos.Invitation;
import CuentasClaras.CuentasClaras.Modelos.User;
import CuentasClaras.CuentasClaras.Request.AddMemberDTO;
import CuentasClaras.CuentasClaras.Services.GroupService;

@RestController
@RequestMapping("/group")
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Group group){
		return groupService.save(group);
	}
	
	@PostMapping("/addMember/{id}")
	public ResponseEntity<?> addMember(@PathVariable int id, @RequestBody AddMemberDTO invitationDto){
		Invitation invitation = new Invitation();
		invitation.setGroup(invitationDto.isGroup());
		invitation.setInviteName(invitationDto.inviteName());
		User user = new User();
		user.setId(invitationDto.userId());
		invitation.setUser(user);
		invitation.setState(false);
		return groupService.addMember(id, invitation);
	}
	
	@PostMapping("/edit")
	public ResponseEntity<?> edit (@RequestBody Group group){
		return groupService.edit(group);
	}
	
	@GetMapping("/findByName/{name}")
	public ResponseEntity<?> findByName(@PathVariable String name) {
		ResponseEntity<?> group = groupService.findByname(name);
		return group;
	}
	
	
}
