package CuentasClaras.CuentasClaras.Modelos;

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
	@Column(name = "inviteUser")
	private String inviteUser;
	
	//receptor
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
	
	public int getId() {
		return id;
	}

	public String getInviteUser() {
		return inviteUser;
	}

	public void setInviteUser(String inviteUser) {
		this.inviteUser = inviteUser;
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

	public Invitation(String inviteUser,boolean isGroup, User identifier) {
		super();
		this.inviteUser = inviteUser;
		this.isGroup = isGroup;
		this.state = false;
	}

	public Invitation() {
		super();
	}

	public void accept() {
		this.state = true;
	}

}
