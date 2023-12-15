package CuentasClaras.CuentasClaras.Modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;


@Entity
@Access(AccessType.FIELD)
@Table(name = "suggestions")
public class Suggestion  {
    
    /**
	 * 
	 */

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

	//Tu sugerido
    @ManyToOne
    @JoinColumn(name = "userSuggestId")
    private User user;

    //Vos
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "suggestId")
    private User suggest;
    
    public Suggestion(User user, User suggest) {
        this.user = user;
        this.suggest = suggest;
    }
    
    public User getUser(){
        return this.user;
    }
    
    public void setUser(User user){
        this.user=user;
    }
    
    public User getSuggest(){
        return this.suggest;
    }
    
    public void setSuggest(User suggest){
        this.suggest=suggest;
    }
    
    
}
