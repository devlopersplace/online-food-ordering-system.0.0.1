package com.example.foodordering.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.foodordering.dto.MenuItemRequestDTO;
import com.example.foodordering.dto.MenuItemResponseDTO;
import com.example.foodordering.model.MenuItem;
import com.example.foodordering.service.MenuService;

/*These remain identical. You are still telling Spring this is a JSON-based controller, 
 * the base URL is /api/menu, and you're allowing the Frontend to talk to the Backend (CORS).*/
@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = "*")
public class MenuController {

    private final MenuService service;

    public MenuController(MenuService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<MenuItemResponseDTO> add(@RequestBody MenuItemRequestDTO dto) {
        MenuItem saved = service.create(dto);

        //The service is your business logic layer. When you call .create(dto), you are handing off the "Request DTO" 
        //(the name, price, and category the user typed in) to the Service manager.
        MenuItemResponseDTO response = new MenuItemResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getPrice(),
                saved.getCategory()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // READ ALL
    @GetMapping
    public List<MenuItemResponseDTO> list() {
        return service.getAll()
                .stream()
                .map(item -> new MenuItemResponseDTO(
                        item.getId(),
                        item.getName(),
                        item.getPrice(),
                        item.getCategory()
                ))
                .collect(Collectors.toList());
    }

    // READ BY ID
    @GetMapping("/{id}")
    public MenuItemResponseDTO get(@PathVariable Long id) {
        MenuItem item = service.getById(id);

        return new MenuItemResponseDTO(
                item.getId(),
                item.getName(),
                item.getPrice(),
                item.getCategory()
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }
}




//package com.example.foodordering.controller;
//
//import java.util.List;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.foodordering.dto.MenuItemRequestDTO;
//import com.example.foodordering.dto.MenuItemResponseDTO;
//import com.example.foodordering.model.MenuItem;
//import com.example.foodordering.service.MenuService;
//
//@RestController  //it tells spring that this class is a controller and the return value should be directly
////converted to JSON format
//@RequestMapping("/api/menu") //This set the base address for everything in the class to talk to this controller
//// the URL will start with http://localhost:8080/api/menu
//@CrossOrigin(origins = "*") // This is the life saver for the web devs It allows your Frontend to talk to your backend
//// even if they run in different ports. currently we used "*" means "allow everyone" but in a real production app, we would
////replace the * with your specific frontend URL for security. 
//
//public class MenuController {
//	
//	private final MenuService service;
//	
//	public MenuController(MenuService service) {
//		this.service = service;
//	}
//	
////	@PostMapping
////	public MenuItem add(@RequestBody MenuItem item) { //@RequestBody takes the JSON sent by the user (e.g., {"name": "Pizza", "price": 10}) and automatically turns it into a Java MenuItem object.
////		return service.create(item);
////	}
//	
//	
//	@PostMapping
//	public ResponseEntity<MenuItemResponseDTO> add(@RequestBody MenuItemRequestDTO dto) {
//	    MenuItem saved = service.create(dto);
//
//	    MenuItemResponseDTO response =
//	        new MenuItemResponseDTO(
//	            saved.getId(),
//	            saved.getName(),
//	            saved.getPrice(),
//	            saved.getCategory()
//	        );
//
//	    return ResponseEntity.status(HttpStatus.CREATED).body(response);
//	}
//
//	
//	
//	@GetMapping
//	public List<MenuItem> list(){
//		return service.getAll();
//	}
//	
//	@GetMapping("/{id}") //This is a Path Variable. If the user goes to /api/menu/5, the number 5 gets assigned to the Long id variable.
//    public MenuItem get(@PathVariable Long id) { //This annotation tells Spring to grab the value from the URL and pass it into the method.
//        return service.getById(id);
//    }
//	
//	@DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        service.delete(id);
//        /*Use @PostMapping for create operations, @GetMapping for read, 
//         * @PutMapping for update, and @DeleteMapping for delete to
//         *  keep your REST API clean and consistent.*/
//        
//        
//}
