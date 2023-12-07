package CuentasClaras.CuentasClaras.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import CuentasClaras.CuentasClaras.Interfaces.ICategory;
import CuentasClaras.CuentasClaras.Interfaces.IGroup;
import CuentasClaras.CuentasClaras.Interfaces.IUser;
import CuentasClaras.CuentasClaras.Modelos.Group;
import CuentasClaras.CuentasClaras.Modelos.Invitation;
import CuentasClaras.CuentasClaras.Modelos.User;
import CuentasClaras.CuentasClaras.Services.GroupService;

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

	public ResponseEntity<?> edit(Group group) {
		Group groupSearched = groupService.findById(group.getId()).orElse(null);
		if (groupSearched != null) {
			groupService.save(group);
			return new ResponseEntity<Group>(group, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Group not found", HttpStatus.BAD_REQUEST);
		}
	}
}
