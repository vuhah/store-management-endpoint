package store.management.storemanagement.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import store.management.storemanagement.entity.Category;

public interface CategoryRepository extends MongoRepository<Category, ObjectId>{
    interface CategoryProjection {
        Integer getIntId();
        String getName();
    }
    List<CategoryProjection> findAllBy();
}