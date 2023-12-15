package CuentasClaras.CuentasClaras.Modelos;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.*;


@Entity
@Access(AccessType.FIELD)
@Table(name = "friendships")
@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class, property = "id")
public class Friendship {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

	//Tu amigo
    @ManyToOne
    @JoinColumn(name = "userFriendId")
    private User user;

	//Vos
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "friendId")
    private User friend;
    
    public Friendship(User user, User friend) {
        this.user = user;
        this.friend = friend;
    }
    
    public Friendship() {
        super();
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public User getFriend() {
        return friend;
    }
    
    public void setFriend(User friend){
        this.friend = friend;
    }

	public int getId() {
		return id;
	}    
    
    
}
