package CuentasClaras.CuentasClaras.Modelos;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name = "invitations")
public class Invitation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "isGroup")
	private boolean isGroup;


	@Column(name = "state")
	private boolean state;
	
	//emisor
	@Column(name = "inviteName")
	private String inviteName;
	
	//receptor
	@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
	
	public int getId() {
		return id;
	}

	public String getInviteName() {
		return inviteName;
	}

	public void setInviteName(String inviteName) {
		this.inviteName = inviteName;
	}

	public boolean isGroup() {
		return isGroup;
	}

	public void setGroup(boolean isGroup) {
		this.isGroup = isGroup;
	}


	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public Invitation(String inviteName,boolean isGroup, User identifier) {
		super();
		this.inviteName = inviteName;
		this.isGroup = isGroup;
		this.user = identifier;
		this.state = false;
	}

	public Invitation() {
		super();
	}

	public void accept() {
		this.state = true;
	}


	public User getUser(){
		return this.user;
	}

	public void setUser(User user){
		this.user = user;
	}
	
	
}
