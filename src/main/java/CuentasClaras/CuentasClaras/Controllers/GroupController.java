package CuentasClaras.CuentasClaras.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CuentasClaras.CuentasClaras.Interfaces.ICategory;
import CuentasClaras.CuentasClaras.Interfaces.IGroup;
import CuentasClaras.CuentasClaras.Interfaces.IUser;
import CuentasClaras.CuentasClaras.Modelos.Group;
import CuentasClaras.CuentasClaras.Modelos.User;

@RestController
@RequestMapping("/group")
public class GroupController {
	@Autowired
	private IGroup groupService;
	@Autowired
	private IUser userService;
	@Autowired
	private ICategory categoryService;
	
	@PostMapping("/save")
	public ResponseEntity<Group> save(@RequestBody Group group){
		if ((categoryService.findByname(group.getCategory().getName()).orElse(null)) == null){
			return new ResponseEntity<Group>(HttpStatus.BAD_REQUEST);
		}else {
			group.setCategory(categoryService.findByname(group.getCategory().getName()).get());
		}
		groupService.save(group);
		return new ResponseEntity<Group>(group,HttpStatus.OK);	
	}
	
	@PostMapping("/addMember/{id}")
	public ResponseEntity<?> addMember(@PathVariable int id, @RequestBody User userName  ){
		Group group = groupService.findById(id).orElse(null);
		if (group != null) {
			User user = (User) userService.findByuserName(userName.getUsername()).orElse(null);
			if (user != null) {
				group.addMember(user);
				user.addGroups(group);
				groupService.save(group);
				userService.save(user);
				return new ResponseEntity<Group>(group,HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("User not found",HttpStatus.BAD_REQUEST);
			}
		}else {
			return new ResponseEntity<String>("Group not found",HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/edit")
	public ResponseEntity<?> edit (@RequestBody Group group){
		Group groupSearched = groupService.findById(group.getId()).orElse(null);
		if (groupSearched != null) {
			groupService.save(group);
			return new ResponseEntity<Group>(group,HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Group not found",HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
