package rma.project.rma.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import rma.project.rma.Entity.User;
import rma.project.rma.service.UserService;


/**
 * Created by gerli on 20/05/2018.
 */
@Controller
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @GetMapping("/create")
    public String newuser() {
        return "create";
    }

    @PostMapping("/newuser")
    public ModelAndView create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser = service.create(user);
        ModelAndView mv = new ModelAndView("newuser");
        mv.getModel().put("user", service.findOne(newUser.getId()));
        return mv;
    }

    @GetMapping("/newuser")
    public String viewUser(@PathVariable("id") int id, Model model) {
        model.addAttribute(service.findOne(id));
        return "newuser/{id}";
    }
}
