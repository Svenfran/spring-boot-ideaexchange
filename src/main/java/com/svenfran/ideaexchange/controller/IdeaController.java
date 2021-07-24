package com.svenfran.ideaexchange.controller;

import com.svenfran.ideaexchange.entity.Idea;
import com.svenfran.ideaexchange.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class IdeaController {

    @Autowired
    private IdeaService ideaService;

    @GetMapping("/ideas")
    public ResponseEntity<List<Idea>> getAllIdeas() {
        List<Idea> ideas = ideaService.getAllIdeas();
        return new ResponseEntity<>(ideas, HttpStatus.OK);
    }

    @PostMapping("/ideas/add")
    public ResponseEntity<Idea> addIdea(@RequestBody Idea idea) {
        Idea newIdea = ideaService.addIdea(idea);
        return new ResponseEntity<>(newIdea, HttpStatus.CREATED);
    }

    @GetMapping("/ideas/{id}")
    public ResponseEntity<Idea> getIdeaById(@PathVariable("id") Long id) {
        Idea idea = ideaService.getIdeaById(id);
        return new ResponseEntity<>(idea, HttpStatus.OK);
    }

    @PutMapping("/ideas/update")
    public ResponseEntity<Idea> updateIdea(@RequestBody Idea idea) {
        Idea updateIdea = ideaService.updateIdea(idea);
        return new ResponseEntity<>(updateIdea, HttpStatus.OK);
    }

    @DeleteMapping("ideas/delete/{id}")
    public ResponseEntity<Idea> deleteIdea(@PathVariable("id") Long id) {
        ideaService.deleteIdea(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
