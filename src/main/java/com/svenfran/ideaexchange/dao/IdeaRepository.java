package com.svenfran.ideaexchange.dao;


import com.svenfran.ideaexchange.entity.Idea;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IdeaRepository extends CrudRepository<Idea, Long> {

    List<Idea> findAllByOrderByDateCreatedDesc();
    
    // Query muss noch erweitert werden. Filtern nach mehreren Categorien und typ (is_idea)
    @Query(value =
            "SELECT * FROM Idea " +
            "INNER JOIN idea_category ic ON ic.idea_id = idea.id " +
            "INNER JOIN Category ON ic.category_id = category.id " +
            "WHERE category.id IN :ids", nativeQuery = true)
    List<Idea> findAllByCategoryIds(@Param("ids") List<Long> ids);

    List<Idea> findByCategoriesId(Long categoryId);

    @Query(value =
            "SELECT * FROM Idea WHERE " +
            "lower(title) LIKE lower(CONCAT('%', :query, '%')) OR " +
            "lower(description) LIKE lower(CONCAT('%', :query, '%'))", nativeQuery = true)
    List<Idea> findBySearchQuery(@Param("query") String query);

//    List<Idea> findByTitleIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(@RequestParam("query") String query);
}
