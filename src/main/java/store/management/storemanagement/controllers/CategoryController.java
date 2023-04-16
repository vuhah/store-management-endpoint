package store.management.storemanagement.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import store.management.storemanagement.services.CategoryService;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<Map<String, Object>>getCategories(){
        Map<String, Object> data = new HashMap<String, Object>();
        List<Map<String, Object>> result =  categoryService.getAllCategories().stream()
            .map(c -> {
                Map<String, Object> categoryMap = new HashMap<>();
                categoryMap.put("id", c.getIntId());
                categoryMap.put("name", c.getName());
                return categoryMap;
            })
            .collect(Collectors.toList());
        data.put("data", result);
        return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
    }
}
