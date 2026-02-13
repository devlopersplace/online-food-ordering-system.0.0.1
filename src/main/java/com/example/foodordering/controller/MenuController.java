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
