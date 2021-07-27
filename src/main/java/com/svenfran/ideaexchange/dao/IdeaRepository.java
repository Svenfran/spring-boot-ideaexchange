package com.svenfran.ideaexchange.dao;


import com.svenfran.ideaexchange.entity.Idea;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IdeaRepository extends CrudRepository<Idea, Long> {

    List<Idea> findAllByOrderByDateCreatedDesc();

    @Query(value =
            "SELECT * FROM Idea " +
            "INNER JOIN idea_category ic ON ic.idea_id = idea.id " +
            "INNER JOIN Category ON ic.category_id = category.id " +
            "WHERE category.id IN :ids", nativeQuery = true)
    List<Idea> findAllByCategoryIds(@Param("ids") List<Long> ids);

    List<Idea> findByCategoriesId(Long categoryId);
}
