package store.management.storemanagement.services;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
// import org.springframework.data.mongodb.core.query.Criteria;
// import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import store.management.storemanagement.entity.User;
import store.management.storemanagement.repositories.UserRepository;


@Service
public class UserService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private UserRepository userRepository;

    public List<User> findCountUsersByParams(String fname, String lname, String uname, String status) {
        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").regex(fname, "i"))
             .addCriteria(Criteria.where("lastName").regex(lname, "i"))
             .addCriteria(Criteria.where("username").regex(uname, "i"))
             .addCriteria(Criteria.where("status").regex(status, "i"));
        return mongoTemplate.find(query, User.class);
    }

    public Map<String, Object> findUsersByParams(String fname, String lname, String uname, String status, Integer page) {
        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").regex(fname, "i"))
             .addCriteria(Criteria.where("lastName").regex(lname, "i"))
             .addCriteria(Criteria.where("username").regex(uname, "i"))
             .addCriteria(Criteria.where("status").regex(status, "i"));
             
        List<User> users = mongoTemplate.find(query, User.class);


        Integer startIndex = (page - 1) * 5;
        Integer endIndex = Math.min(startIndex + 5, users.size());
        Integer totalSize = (int) Math.ceil(users.size()/5)+1;
        Integer pageNumber = page;   

        Map <String, Object> data = new HashMap<>();
        data.put("totalPage", totalSize);
        data.put("pageNumber", pageNumber);
        data.put("data",users.subList(startIndex, endIndex));
        return data;
    }

    public void deleteUser(ObjectId id){
        userRepository.deleteById(id);
    } 

    public User updateUser(ObjectId id, String fname, String lname, String status){
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update()
            .set("firstName", fname)
            .set("lastName", lname)
            .set("status", status);
        User updatedUser = mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), User.class);
        return updatedUser;
    }
}
