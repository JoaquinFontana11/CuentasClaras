package CuentasClaras.CuentasClaras.Controllers;

import java.util.*;
import java.util.stream.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import CuentasClaras.CuentasClaras.Interfaces.*;
import CuentasClaras.CuentasClaras.Modelos.*;

@RestController
@RequestMapping("/suggestions")
public class SuggestionController {
    @Autowired
    IUser userService;
    @Autowired
    IGroup groupService;
    @Autowired
    ISuggestion suggestionService;

    @PostMapping("/findSuggestions/{id}")
    public ResponseEntity<?> findSuggestions(@PathVariable int idUserToFindSuggestions){
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
