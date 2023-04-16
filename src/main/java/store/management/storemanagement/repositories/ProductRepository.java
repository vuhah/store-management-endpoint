package store.management.storemanagement.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import store.management.storemanagement.entity.Product;

public interface ProductRepository extends MongoRepository<Product, ObjectId> {
    public List<Product> findByNameAndCategoryIdAndPrice(String name, int categoryId, int price);
    public List<Product> findByNameAndCategoryId(String name, int categoryId);
    public List<Product> findByNameAndCategoryIdOrderByPriceAsc(String name, Integer categoryId);
    public List<Product> findByNameAndCategoryIdOrderByPriceDesc(String name, Integer categoryId);
    public List<Product> findAllByOrderByPriceAsc();
    public List<Product> findAllByOrderByPriceDesc();
    public List<Product> findByCategoryIdOrderByPriceAsc(int cateogryId);
    public List<Product> findByCategoryIdOrderByPriceDesc(int cateogryId);
}
