package CuentasClaras.CuentasClaras.ServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import CuentasClaras.CuentasClaras.Interfaces.ISuggestion;
import CuentasClaras.CuentasClaras.Interfaces.IUser;
import CuentasClaras.CuentasClaras.Modelos.Group;
import CuentasClaras.CuentasClaras.Modelos.Suggestion;
import CuentasClaras.CuentasClaras.Modelos.User;
import CuentasClaras.CuentasClaras.Services.SuggestionService;

@Service
public class SuggestionServiceImpl implements SuggestionService{

	@Autowired
    private IUser userService;
    @Autowired
    private ISuggestion suggestionService;

    public ResponseEntity<?> findSuggestions(int idUserToFindSuggestions){
        User userToFindSuggestions = userService.findById(idUserToFindSuggestions).orElse(null);
        if (userToFindSuggestions == null){
            return new ResponseEntity<String>("User not found",HttpStatus.BAD_REQUEST);
        }
        List<User> suggestions = getNotFriends(userToFindSuggestions);
        suggestions.stream().forEach(s -> { 
            Suggestion suggest = new Suggestion(userToFindSuggestions,s);;
            suggestionService.save(suggest);
        });
        return new ResponseEntity<String>("Suggestions created", HttpStatus.OK);
    }

    private List<User> getNotFriends(User user){
        List<Group> groups = user.getGroups();
        List<User> users = new ArrayList<User>();
        groups.stream().forEach(g -> g.getMembers().stream().forEach(u -> users.add(u)));
        return user.getFriendships().stream().filter(f -> !users.contains(f.getFriend()))
        .collect(Collectors.toList()).stream().map(f -> f.getFriend()).collect(Collectors.toList());
    }
	
}
