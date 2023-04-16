package store.management.storemanagement.repositories;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import store.management.storemanagement.entity.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {
}