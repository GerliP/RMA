//package rma.project.rma.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import rma.project.rma.Entity.User;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * Created by gerli on 16/05/2018.
// */
//@Service
//public class UserService {
//
//        @Autowired
//        private RestTemplate restTemplate;
//
//    @Value("${resource}")
//    private String resource;
//    @Value("${resource}/{id}")
//    private String idResource;
//
//    public List<User> findAll() {
//        return Arrays.stream(restTemplate.getForObject(resource, User[].class)).collect(Collectors.toList());
//    }
//
//    public User create(User user) {
//        return restTemplate.postForObject(resource, user, User.class);
//    }
//
//    public User update(int id, User user) {
//        return restTemplate.exchange(idResource, HttpMethod.PUT, new HttpEntity<>(user), User.class, id).getBody();
//    }
//
//    public void delete(int id) {
//        restTemplate.delete(idResource, id);
//    }
//
//    public User findOne(int id) {
//        return restTemplate.getForEntity(resource, User.class, id).getBody();
//    }
//}
