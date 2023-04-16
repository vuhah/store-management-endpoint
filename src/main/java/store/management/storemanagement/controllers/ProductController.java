package store.management.storemanagement.controllers;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import store.management.storemanagement.entity.Product;
import store.management.storemanagement.services.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> allProduct = productService.allProduct();
        System.out.println(allProduct);
        return new ResponseEntity<List<Product>>(allProduct, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Optional<Product>> getProduct(@PathVariable ObjectId productId) {
        return new ResponseEntity<Optional<Product>>(productService.singleProduct(productId), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody Map<String, Object> requestBody) {
        Integer categoryId = (requestBody.get("categoryId") instanceof Integer)? (Integer)requestBody.get("categoryId"):Integer.parseInt((String)requestBody.get("categoryId"));
        String name = (String) requestBody.get("name");
        Integer quantity = (requestBody.get("quantity") instanceof Integer)? (Integer)requestBody.get("quantity"):Integer.parseInt((String)requestBody.get("quantity"));
        Integer price = (requestBody.get("price") instanceof Integer)?(Integer)requestBody.get("price"):Integer.parseInt((String)requestBody.get("price"));
        String image = (String) requestBody.get("image");
        Product product = new Product(name, image, quantity, price, categoryId);
        return new ResponseEntity<Product>(productService.addProduct(product), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Optional<Product>> updateProduct(@RequestBody Product product){
        return new ResponseEntity<Optional<Product>>(productService.findByIdAndUpdate(product), HttpStatus.OK);
    }

    @PostMapping("/filtered/count")
    public ResponseEntity<Map<String,Object>> filterProduct(@RequestBody Map<String, Object> requestBody) {
        String name = (String)requestBody.get("productName");
        Integer cateogryId = (requestBody.get("categoryId") instanceof Integer)? (Integer)requestBody.get("categoryId"):Integer.parseInt((String)requestBody.get("categoryId"));
        Integer price = (requestBody.get("priceOrder") instanceof Integer)?(Integer)requestBody.get("priceOrder"):Integer.parseInt((String)requestBody.get("priceOrder"));
        Map<String, Object> data = new HashMap<>();
        data.put("data", productService.findWithNameCategory(cateogryId, name, price));
        return ResponseEntity.ok(data);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable ObjectId id){
        productService.deleteProduct(id);   
        return ResponseEntity.status(HttpStatus.OK).build();
    }   

    @PutMapping("/{id}")
    public ResponseEntity<Product> putProduct(@PathVariable ObjectId id, @RequestBody Map<String, Object> requestBody) {
        Integer categoryId = (requestBody.get("categoryId") instanceof Integer)? (Integer)requestBody.get("categoryId"):Integer.parseInt((String)requestBody.get("categoryId"));
        String name = (String) requestBody.get("name");
        Integer quantity = (requestBody.get("quantity") instanceof Integer)? (Integer)requestBody.get("quantity"):Integer.parseInt((String)requestBody.get("quantity"));
        Integer price = (requestBody.get("price") instanceof Integer)?(Integer)requestBody.get("price"):Integer.parseInt((String)requestBody.get("price"));
        String image = (String) requestBody.get("image");
        Product product = new Product(id, name, image, quantity, price, categoryId);
        return new ResponseEntity<Product>(productService.updateProduct(product), HttpStatus.OK);
    }

    @PostMapping("/filtered")
    public ResponseEntity<Map<String,Object>> getFilteredProducts(
        @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
        @RequestBody Map<String, Object> requestBody) {

        String name = (String)requestBody.get("productName");
        Integer cateogryId = (requestBody.get("categoryId") instanceof Integer)? (Integer)requestBody.get("categoryId"):Integer.parseInt((String)requestBody.get("categoryId"));
        Integer price = (requestBody.get("priceOrder") instanceof Integer)?(Integer)requestBody.get("priceOrder"):Integer.parseInt((String)requestBody.get("priceOrder"));

        Map<String, Object> data = new HashMap<>();
        data.put("data",productService.getProducts(cateogryId, name, price, pageNumber));
        return ResponseEntity.ok(data);
    }   
}
 