package store.management.storemanagement.services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import store.management.storemanagement.entity.Product;
import store.management.storemanagement.repositories.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> allProduct(){
        return productRepository.findAll();
    }
    public Optional<Product> singleProduct(ObjectId productId){
        return productRepository.findById(productId);
    }
    public Product addProduct(Product product){
        return productRepository.insert(product);
    }

    public Product updateProduct(Product product){
        Product existingProduct = productRepository.findById(product.getId()).orElse(null);
        existingProduct.setCategoryId(product.getCategoryId());
        existingProduct.setImage(product.getImage());
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());
        productRepository.save(existingProduct);
        return existingProduct;
    }
    public Optional<Product> findByIdAndUpdate(Product product){
        ObjectId id = product.getId();
        Optional<Product> productReturn = productRepository.findById(id);
        if (productReturn == null) return null;
        return Optional.of(productRepository.save(product));
    }
    public void deleteProduct(String id){
        productRepository.deleteById(new ObjectId(id));
    }

    public int findWithNameCategory(int cateogryId, String name, int price){
        List<Product> products = productRepository.findByNameAndCategoryId(name, cateogryId);
        if (cateogryId == 1 && name=="" ){
            return this.allProduct().size();
        }        
        else if (name==""){
            return price==0?productRepository.findByCategoryIdOrderByPriceAsc(cateogryId).size():productRepository.findByCategoryIdOrderByPriceDesc(cateogryId).size();
        }
        return products.size();
    }

    public void deleteProduct(ObjectId id){
        productRepository.deleteById(id);
    } 

    public Map<String, Object> getProducts(Integer categoryId, String productName, Integer priceOrder, int page) {
        List<Product> products ;
        if (categoryId == 1 && productName=="" ){
            products = priceOrder==0?productRepository.findAllByOrderByPriceAsc():productRepository.findAllByOrderByPriceDesc();
        }
        else if (productName==""){
            products = priceOrder==0?productRepository.findByCategoryIdOrderByPriceAsc(categoryId):productRepository.findByCategoryIdOrderByPriceDesc(categoryId);
        }
        else{
            products = priceOrder==0? productRepository.findByNameAndCategoryIdOrderByPriceAsc(productName, categoryId):productRepository.findByNameAndCategoryIdOrderByPriceDesc(productName, categoryId);
        }
        
        Integer startIndex = (page - 1) * 5;
        Integer endIndex = Math.min(startIndex + 5, products.size());
        Integer totalSize = (int) Math.ceil(products.size()/5)+1;
        Integer pageNumber = page;
         
        Map <String, Object> data = new HashMap<>();
        data.put("totalPage", totalSize);
        data.put("pageNumber", pageNumber);
        data.put("data",products.subList(startIndex, endIndex));
        return data;
    }

    
}

