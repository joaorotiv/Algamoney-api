package com.example.algamoney.api.resources;

import com.example.algamoney.api.event.CreatedEventResource;
import com.example.algamoney.api.models.CategoryModel;
import com.example.algamoney.api.models.PersonModel;
import com.example.algamoney.api.repositories.CategoryRepository;
import com.example.algamoney.api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/search")
    public List<CategoryModel>listCategories(){
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryModel> searchById(@PathVariable Long id){
        return categoryRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CategoryModel> create(@RequestBody @Valid CategoryModel category, HttpServletResponse response) {
        CategoryModel savedCategory = categoryRepository.save(category);
        publisher.publishEvent(new CreatedEventResource(this, response, savedCategory.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        categoryRepository.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryModel> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryModel categoryModel) {
        CategoryModel savedCategory = categoryService.updateCategory(id, categoryModel);
        return ResponseEntity.ok(savedCategory);
    }


}
