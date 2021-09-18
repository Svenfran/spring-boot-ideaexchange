package com.svenfran.ideaexchange.controller;

import com.svenfran.ideaexchange.dto.IdeaDto;
import com.svenfran.ideaexchange.entity.Idea;
import com.svenfran.ideaexchange.service.IdeaService;
import com.svenfran.ideaexchange.utils.DtoMapper;
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
    public ResponseEntity<List<IdeaDto>> getAllIdeas() {
        List<Idea> ideas = ideaService.getAllIdeas();
        return DtoMapper.mapToIdeaDtoList(ideas);
    }

    @PostMapping("/ideas/add")
    public ResponseEntity<Idea> addIdea(@RequestBody Idea idea) {
        Idea newIdea = ideaService.addIdea(idea);
        return new ResponseEntity<>(newIdea, HttpStatus.CREATED);
    }

    @GetMapping("/ideas/{id}")
    public ResponseEntity<IdeaDto> getIdeaById(@PathVariable("id") Long id) {
        Idea idea = ideaService.getIdeaById(id);
        return DtoMapper.mapToIdeaDto(idea);
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

    @GetMapping("/search/findIdeasByCategoryAndIdea")
    public ResponseEntity<List<IdeaDto>> getIdeasByCategoryId(@RequestParam("categoryIds") List<Long> categoryIds, @RequestParam("isIdea") boolean isIdea) {
        Integer countCategoryIds = categoryIds.size();
        List<Idea> ideas = ideaService.getIdeasByCategoryIdsAndIdeaFlag(categoryIds, isIdea, countCategoryIds);
        return DtoMapper.mapToIdeaDtoList(ideas);
    }

    @GetMapping("/search/findIdeasByCategory")
    public ResponseEntity<List<IdeaDto>> getIdeasByCategoryId(@RequestParam("categoryIds") List<Long> categoryIds) {
        Integer countCategoryIds = categoryIds.size();
        List<Idea> ideas = ideaService.getIdeasByCategoryIds(categoryIds, countCategoryIds);
        return DtoMapper.mapToIdeaDtoList(ideas);
    }

    @GetMapping("/search/findIdeasByQuery")
    public ResponseEntity<List<IdeaDto>> getIdeasByQuery(@RequestParam("query") String query) {
        List<Idea> ideas = ideaService.getIdeasByQuery(query);
        return DtoMapper.mapToIdeaDtoList(ideas);
    }

}
