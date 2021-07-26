package com.svenfran.ideaexchange.dao;


import com.svenfran.ideaexchange.entity.Idea;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IdeaRepository extends CrudRepository<Idea, Long> {

    List<Idea> findAllByOrderByDateCreatedDesc();

//    @Query(value = "SELECT Idea FROM Idea INNER JOIN idea_category ic ON ic.idea_id = idea.id INNER JOIN Category ON id = ic.category_id WHERE id = :id", nativeQuery = true)
//    List<Idea> findAllByCategoryId(@Param("id") Long id);

    List<Idea> findByCategoriesId(Long categoryId);
}
