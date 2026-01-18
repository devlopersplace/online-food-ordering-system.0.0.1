//This class follows the CRUD pattern

package com.example.foodordering.service;

import com.example.foodordering.dto.MenuItemRequestDTO;
import com.example.foodordering.exception.ResourceNotFoundException;
import com.example.foodordering.model.MenuItem;
import com.example.foodordering.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  //it is the specialization of @Component.

//you are telling the Spring IoC (Inversion of Control) Container:
//Hey Spring, please manage this class for me. Create an instance 
//of it when the app starts, and keep it in your 'toolbox'.
//(the Application Context) so I can use it elsewhere."

public class MenuService {
	
	//This is a constructor based dependency injection.
	/*why? because MenuService needs MenuItemRepository to talk to the database.
	 *  Instead of the service creating the repository itself using new spring injects it */
	private final MenuItemRepository repository;
	public MenuService(MenuItemRepository repository) {
        this.repository = repository;
    }
	//Int tip: Constructor injection is preferred over @Autowired on fields because it makes the class easier to test 
	//(Using Unit Tests)and ensures that the repository is final (Immutable).
	
	
	//The Create Method
	public MenuItem create(MenuItemRequestDTO dto) {

	    MenuItem item = new MenuItem();
	    item.setName(dto.getName());
	    item.setPrice(dto.getPrice());
	    item.setCategory(dto.getCategory());

	    return repository.save(item);
	}

	
	//The read methods
	public List<MenuItem> getAll(){ //getAll(): Returns a List of every item in the menu_items table.
		return repository.findAll();
	}
	
	public MenuItem getById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(""));//findById(id) might not find anything (if the ID doesn't exist).
		//if the item exists, return it; if not,return Item not found here we used RNFE because it avoids NullPointerExceptions later.
	}
	
	
	//The Delete Method.
	public void delete (Long id) { //It takes the ID and removes that specific row from the database.
		repository.deleteById(id); //It returns void because once something is deleted, there is no object to return.
	}
}
