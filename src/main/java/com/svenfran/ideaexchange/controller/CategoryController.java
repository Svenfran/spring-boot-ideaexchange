package com.svenfran.ideaexchange.controller;

import com.svenfran.ideaexchange.entity.Category;
import com.svenfran.ideaexchange.entity.Idea;
import com.svenfran.ideaexchange.service.CategoryService;
import com.svenfran.ideaexchange.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private IdeaService ideaService;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("categories/{id}/ideas")
    public ResponseEntity<List<Idea>> getIdeasByCategory(@PathVariable("id") Long id) {
        List<Idea> ideas = ideaService.getIdeasByCategory(id);
        return new ResponseEntity<>(ideas, HttpStatus.OK);
    }
}
