package com.svenfran.ideaexchange.service;

import com.svenfran.ideaexchange.dao.IdeaRepository;
import com.svenfran.ideaexchange.entity.Idea;
import com.svenfran.ideaexchange.exception.IdeaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IdeaService {

    @Autowired
    private IdeaRepository ideaRepository;

    public List<Idea> getAllIdeas() {
        List<Idea> ideas = new ArrayList<>();
        ideaRepository.findAllByOrderByDateCreatedDesc().forEach(ideas::add);
        return ideas;
    }

    public Idea addIdea(Idea idea) {
        return ideaRepository.save(idea);
    }

    public Idea getIdeaById(Long id) {
        return ideaRepository.findById(id)
                .orElseThrow(() -> new IdeaNotFoundException("Idea with id " + id + " was not found"));
    }

    public Idea updateIdea(Idea idea) {
        return ideaRepository.save(idea);
    }

    public void deleteIdea(Long id) {
        ideaRepository.deleteById(id);
    }
}