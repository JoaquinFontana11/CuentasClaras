package CuentasClaras.CuentasClaras.ServicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import CuentasClaras.CuentasClaras.Interfaces.ICategory;
import CuentasClaras.CuentasClaras.Modelos.Category;
import CuentasClaras.CuentasClaras.Services.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private ICategory categoryService;
	
	public ResponseEntity<?> findAll() {
		List<Category> lista = (List<Category>) categoryService.findAll();
		if (lista.size() == 0) {
			return new ResponseEntity<String>("Categories not found",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Category>>(lista,HttpStatus.OK);
	}

	public ResponseEntity<?> save(Category category) {
		categoryService.save(category);
		Category c = this.categoryService.findByname(category.getName()).orElse(null);
		if (c == null) {
			return new ResponseEntity<String>("Category not created", HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<Category>(c,HttpStatus.OK);
		}
	    
	}

	public ResponseEntity<?> find(int id) {
		Category c = this.categoryService.findById(id).orElse(null);
		if (c == null) {
			return new ResponseEntity<String>("Category not found", HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<Category>(c,HttpStatus.OK);
		}
	}
	
	public ResponseEntity<?> findByName(String name) {
		Category c = this.categoryService.findByname(name).orElse(null);
		if (c == null) {
			return new ResponseEntity<String>("Category not found", HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<Category>(c,HttpStatus.OK);
		}
	}

}
