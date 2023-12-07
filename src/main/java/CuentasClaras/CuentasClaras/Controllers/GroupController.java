package CuentasClaras.CuentasClaras.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CuentasClaras.CuentasClaras.Modelos.Group;
import CuentasClaras.CuentasClaras.Modelos.Invitation;
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
	public ResponseEntity<?> addMember(@PathVariable int id, @RequestBody Invitation invitation){
		return groupService.addMember(id, invitation);
	}
	
	@PostMapping("/edit")
	public ResponseEntity<?> edit (@RequestBody Group group){
		return groupService.edit(group);
	}
	
	
}
