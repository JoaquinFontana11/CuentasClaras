package CuentasClaras.CuentasClaras.Modelos;

import java.lang.annotation.Repeatable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import CuentasClaras.CuentasClaras.Serializers.*;

import jakarta.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name = "users")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User{

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
	
	@JsonIgnore
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
	@JsonSerialize(contentUsing = CustomGroupSerializer.class)
	private List<Group> groups;

	@OneToMany(mappedBy="friend")
	@JsonSerialize(contentUsing = CustomFriendshipSerializer.class)
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

	public void addFriend(Friendship friend) {
		friendships.add(friend);
	}

	
	public void addGroups(Group group) {
		this.groups.add(group);
	}
	
	public void addInvitation(Invitation invitation){
		this.invitations.add(invitation);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public List<Invitation> getInvitations() {
		return invitations;
	}

	public void setInvitations(List<Invitation> invitations) {
		this.invitations = invitations;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
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
	
}

