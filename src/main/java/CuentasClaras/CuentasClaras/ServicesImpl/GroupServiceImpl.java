package CuentasClaras.CuentasClaras.ServicesImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import CuentasClaras.CuentasClaras.Interfaces.ICategory;
import CuentasClaras.CuentasClaras.Interfaces.IGroup;
import CuentasClaras.CuentasClaras.Interfaces.IUser;
import CuentasClaras.CuentasClaras.Modelos.*;
import CuentasClaras.CuentasClaras.Services.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private IGroup groupService;
	@Autowired
	private IUser userService;
	@Autowired
	private ICategory categoryService;

	public ResponseEntity<?> save(Group group) {
		if ((categoryService.findByname(group.getCategory().getName()).orElse(null)) == null) {
			return new ResponseEntity<String>("Category not found", HttpStatus.BAD_REQUEST);
		} else {
			group.setCategory(categoryService.findByname(group.getCategory().getName()).get());
		}
		groupService.save(group);
		return new ResponseEntity<Group>(group, HttpStatus.OK);
	}

	public ResponseEntity<?> addMember(int id, Invitation invitation) {
		Group group = groupService.findById(id).orElse(null);
		if (group != null) {
			User user = (User) userService.findById(invitation.getUser().getId()).orElse(null);
			if (user != null) {
				invitation.setUser(user);
				user.addInvitation(invitation);
				userService.save(user);
				return new ResponseEntity<Invitation>(invitation, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("User not found", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<String>("Group not found", HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> edit(Group group,String categoryName) {
		Group groupSearched = groupService.findById(group.getId()).orElse(null);
		Optional<Category> categorySearched = categoryService.findByname(categoryName);
		if(categorySearched.isEmpty()) {
			return new ResponseEntity<String>("Category not found", HttpStatus.BAD_REQUEST);
		}
		
		if (groupSearched != null) {
			group.setMembers(groupSearched.getMembers());//Si queres agregar o eliminar un miembro, usar los endpoints correspondientes en el controller
			group.setCategory(categorySearched.get());
			return this.save(group);
		} else {
			return new ResponseEntity<String>("Group not found", HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> findByname(String name) {
		Optional<Group> group = groupService.findByname(name);
		if(group.isEmpty()) 
			return new ResponseEntity<String>("Group not found", HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<Group>(group.get(), HttpStatus.OK);
	}
	
	public ResponseEntity<Group> findById(int id) {
		Group group = groupService.findById(id).orElse(null);
		if (group == null)
			return new ResponseEntity<Group>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Group>(group, HttpStatus.OK);
	}
}
