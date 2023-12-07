package CuentasClaras.CuentasClaras.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import CuentasClaras.CuentasClaras.Services.SuggestionService;

@RestController
@RequestMapping("/suggestions")
public class SuggestionController {
    @Autowired
    private SuggestionService suggestionService;

    @PostMapping("/findSuggestions/{id}")
    public ResponseEntity<?> findSuggestions(@PathVariable int idUserToFindSuggestions){
    	return this.suggestionService.findSuggestions(idUserToFindSuggestions);
    }
}
