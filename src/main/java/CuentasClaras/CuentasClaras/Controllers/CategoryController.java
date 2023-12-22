package CuentasClaras.CuentasClaras.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CuentasClaras.CuentasClaras.Modelos.Category;
import CuentasClaras.CuentasClaras.Services.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll(){
	  return this.categoryService.findAll();
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Category category){
		return this.categoryService.save(category);
	}
	
	@GetMapping("find/{id}")
	public ResponseEntity<?> find(@PathVariable int id){
		return this.categoryService.find(id);
	}
	
	@GetMapping("findByName/{name}")
	public ResponseEntity<?> findByName(@PathVariable String name){
		return this.categoryService.findByName(name);
	}
	
	
}
