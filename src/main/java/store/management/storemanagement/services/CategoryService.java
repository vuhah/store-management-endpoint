package store.management.storemanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import store.management.storemanagement.repositories.CategoryRepository;
import store.management.storemanagement.repositories.CategoryRepository.CategoryProjection;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryProjection> getAllCategories(){
        List<CategoryProjection> categories = categoryRepository.findAllBy();
        return categories;  
    }
}
