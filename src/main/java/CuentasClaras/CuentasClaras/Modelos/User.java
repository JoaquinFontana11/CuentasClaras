package CuentasClaras.CuentasClaras.Modelos;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "userName", unique = true)
	private String userName;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "money")
	private double money;

	@OneToMany(mappedBy="user",cascade = CascadeType.ALL)
	private List<Invitation> invitations;
	

	@OneToMany(mappedBy="debtor",cascade = CascadeType.ALL)
	private List<Payment> payments;
	

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			name = "user_groups", 
			joinColumns = @JoinColumn(name = "user_id"), 
			inverseJoinColumns = @JoinColumn(name = "groups_id")
	)
	private List<Group> groups;

	@OneToMany(mappedBy="friend")
	private List<Friendship> friendships;
	

	@OneToMany(mappedBy="suggest")
	private List<Suggestion> suggestions;
	
	public User(String username, String name, String lastName, String email, String password) {
		super();
		this.userName = username;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public void addFriend(Friendship friend) {
		friendships.add(friend);
	}

	public List<Friendship> getFriendships() {
		return friendships;
	}

	public void setFriendships(List<Friendship> friendships) {
		this.friendships = friendships;
	}

	public List<Suggestion> getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(List<Suggestion> suggestions) {
		this.suggestions = suggestions;
	}
	
	public void addGroups(Group group) {
		this.groups.add(group);
	}
	
	public void addInvitation(Invitation invitation){
		this.invitations.add(invitation);
	}

	public List<Group> getGroups(){
		return groups;
	}
}

