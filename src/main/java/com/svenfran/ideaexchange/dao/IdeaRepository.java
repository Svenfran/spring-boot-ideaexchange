package com.svenfran.ideaexchange.dao;


import com.svenfran.ideaexchange.entity.Idea;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IdeaRepository extends CrudRepository<Idea, Long> {

    List<Idea> findAllByOrderByDateCreatedDesc();
    
    // Query muss noch erweitert werden. Filtern nach mehreren Categorien und typ (is_idea)
    @Query(value =
            "SELECT *, count(*) FROM Idea " +
            "INNER JOIN idea_category ic ON ic.idea_id = idea.id " +
            "INNER JOIN Category ON ic.category_id = category.id " +
            "WHERE category.id IN :ids AND is_idea = :isIdea " +
            "GROUP BY idea.id HAVING count(*) = :countCategoryIds " +
            "ORDER BY date_created DESC", nativeQuery = true)
    List<Idea> findAllByCategoryIds(@Param("ids") List<Long> ids,
                                    @Param("isIdea") boolean isIdea,
                                    @Param("countCategoryIds") Integer countCategoryIds);

    List<Idea> findByCategoriesIdOrderByDateCreatedDesc(Long categoryId);

    @Query(value =
            "SELECT * FROM Idea WHERE " +
            "lower(title) LIKE lower(CONCAT('%', :query, '%')) OR " +
            "lower(description) LIKE lower(CONCAT('%', :query, '%')) " +
            "ORDER BY date_created DESC", nativeQuery = true)
    List<Idea> findBySearchQuery(@Param("query") String query);

//    List<Idea> findByTitleIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(@RequestParam("query") String query);
}
