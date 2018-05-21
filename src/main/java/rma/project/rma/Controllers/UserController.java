//package rma.project.rma.Controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import rma.project.rma.Entity.User;
//import rma.project.rma.service.UserService;
//
//import javax.validation.Valid;
//
///**
// * Created by gerli on 20/05/2018.
// */
//@Controller
//@RequestMapping("/")
//public class UserController {
//
//    @Autowired
//    private UserService service;
//
//
//    @RequestMapping(method = RequestMethod.PUT)
//    public String update(@RequestParam int id, User user) {
//        service.update(id, user);
//        return "redirect:/";
//    }
//
//    @RequestMapping(method = RequestMethod.DELETE)
//    public String delete(@RequestParam int id) {
//        service.delete(id);
//        return "redirect:/";
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public String create(@Valid @ModelAttribute("create") User user) {
//        service.create(user);
//        return "redirect:/";
//    }
//
//    @GetMapping("/newuser/{id}")
//    public String viewUser(@PathVariable("id") int id, Model model) {
//        model.addAttribute(service.findOne(id));
//        return "newuser";
//    }
//}
