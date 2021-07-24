package com.svenfran.ideaexchange.service;

import com.svenfran.ideaexchange.dao.CategoryRepository;
import com.svenfran.ideaexchange.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAllByOrderByNameAsc().forEach(categories::add);
        return categories;
    }
}
