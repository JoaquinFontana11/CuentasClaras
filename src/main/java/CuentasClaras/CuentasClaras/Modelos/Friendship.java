package CuentasClaras.CuentasClaras.Modelos;

import java.util.*;
import jakarta.persistence.*;


@Entity
@Access(AccessType.FIELD)
@Table(name = "friendships")
public class Friendship {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "userFriendId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "friendId")
    private User friend;
    
    public Friendship(int id, User user, User friend) {
        this.id = id;
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
}
