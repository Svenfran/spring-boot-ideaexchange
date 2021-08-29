package com.svenfran.ideaexchange.controller;

import com.svenfran.ideaexchange.dto.IdeaDTO;
import com.svenfran.ideaexchange.entity.Idea;
import com.svenfran.ideaexchange.service.IdeaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class IdeaController {

    @Autowired
    private IdeaService ideaService;

    @GetMapping("/ideas")
    public ResponseEntity<List<IdeaDTO>> getAllIdeas() {
        List<Idea> ideas = ideaService.getAllIdeas();
        return ideasToIdeaDTOS(ideas);
    }

    @PostMapping("/ideas/add")
    public ResponseEntity<Idea> addIdea(@RequestBody Idea idea) {
        Idea newIdea = ideaService.addIdea(idea);
        return new ResponseEntity<>(newIdea, HttpStatus.CREATED);
    }

    @GetMapping("/ideas/{id}")
    public ResponseEntity<IdeaDTO> getIdeaById(@PathVariable("id") Long id) {
        Idea idea = ideaService.getIdeaById(id);
        IdeaDTO ideaDTO = new IdeaDTO();

        if (idea != null) {
            if (idea.isOpen()) {
                BeanUtils.copyProperties(idea, ideaDTO);
            } else {
                BeanUtils.copyProperties(idea, ideaDTO, "description");
            }
            return ResponseEntity.ok(ideaDTO);
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/ideas/update")
    public ResponseEntity<Idea> updateIdea(@RequestBody Idea idea) {
        Idea updateIdea = ideaService.updateIdea(idea);
        return new ResponseEntity<>(updateIdea, HttpStatus.OK);
    }

    @DeleteMapping("/ideas/delete/{id}")
    public ResponseEntity<Idea> deleteIdea(@PathVariable("id") Long id) {
        ideaService.deleteIdea(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search/findIdeasByCategory")
    public ResponseEntity<List<IdeaDTO>> getIdeasByCategoryId(@RequestParam("categoryIds") List<Long> categoryIds, @RequestParam("isIdea") boolean isIdea) {
        Integer countCategoryIds = categoryIds.size();
        List<Idea> ideas = ideaService.getIdeasByCategoryIds(categoryIds, isIdea, countCategoryIds);
        return ideasToIdeaDTOS(ideas);
    }

    @GetMapping("/search/findIdeasByQuery")
    public ResponseEntity<List<IdeaDTO>> getIdeasByQuery(@RequestParam("query") String query) {
        List<Idea> ideas = ideaService.getIdeasByQuery(query);
        return ideasToIdeaDTOS(ideas);
    }

    public ResponseEntity<List<IdeaDTO>> ideasToIdeaDTOS(List<Idea> ideas) {
        List<IdeaDTO> ideaDTOS = new ArrayList<>();

        for (Idea idea : ideas) {
            IdeaDTO ideaDTO = new IdeaDTO();
            if (idea.isOpen()) {
                BeanUtils.copyProperties(idea, ideaDTO);
            } else {
                BeanUtils.copyProperties(idea, ideaDTO, "description");
            }
            ideaDTOS.add(ideaDTO);
        }
        return ResponseEntity.ok(ideaDTOS);
    }
}
