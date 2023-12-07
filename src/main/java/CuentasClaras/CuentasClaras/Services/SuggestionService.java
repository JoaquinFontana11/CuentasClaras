package CuentasClaras.CuentasClaras.Services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface SuggestionService {
	
	public ResponseEntity<?> findSuggestions(int idUserToFindSuggestions);
}
