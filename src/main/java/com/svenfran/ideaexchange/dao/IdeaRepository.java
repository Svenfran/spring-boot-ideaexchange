package com.svenfran.ideaexchange.dao;


import com.svenfran.ideaexchange.entity.Idea;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IdeaRepository extends CrudRepository<Idea, Long> {

    List<Idea> findAllByOrderByDateCreatedDesc();

}
