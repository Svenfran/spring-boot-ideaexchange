package com.svenfran.ideaexchange.dao;

import com.svenfran.ideaexchange.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    List<Category> findAllByOrderByNameAsc();
}
