package store.management.storemanagement.controllers;

import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import store.management.storemanagement.services.UserService;

@RestController
@RequestMapping("/api/customers")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/filtered/count")
    public ResponseEntity<Map<String,Integer>> getCount(@RequestBody Map<String, String> requestBody){
        Map<String, Integer> result = new HashMap<>();
        String fname = requestBody.get("firstName");
        String lname = requestBody.get("lastName");
        String uname = requestBody.get("username");
        String status = requestBody.get("status");
        result.put("data", userService.findCountUsersByParams(fname,lname,uname,status).size());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/filtered")
    public ResponseEntity<Map<String,Object>> getFilteredProducts(
        @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
        @RequestBody Map<String, String> requestBody) {

        String fname = requestBody.get("firstName");
        String lname = requestBody.get("lastName");
        String uname = requestBody.get("username");
        String status = requestBody.get("status");

        Map<String, Object> data = new HashMap<>();
        data.put("data",userService.findUsersByParams(fname, lname, uname,status, pageNumber));
        return ResponseEntity.ok(data);
    }   

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable ObjectId id){
        userService.deleteUser(id);   
        return ResponseEntity.status(HttpStatus.OK).build();
    }   

    @PutMapping("/{id}")
    public ResponseEntity<Map<String,Object>> putProduct(@PathVariable ObjectId id, @RequestBody Map<String, String> requestBody) {
        String fname = requestBody.get("firstName");
        String lname = requestBody.get("lastName");
        String status = requestBody.get("status");
        Map<String, Object> data = new HashMap<>();
        data.put("data", userService.updateUser(id, fname, lname,status));
        return ResponseEntity.ok(data);
    }

}
