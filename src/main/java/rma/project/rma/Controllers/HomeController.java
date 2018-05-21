package rma.project.rma.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rma.project.rma.Entity.Device;
import rma.project.rma.Entity.User;
import rma.project.rma.Repos.DeviceRepository;
import rma.project.rma.Repos.UserRepository;
import rma.project.rma.Config.UserServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by gerli on 20/02/2018.
 */
@Controller
public class HomeController {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl userService;
    @Autowired
    public DeviceRepository deviceRepository;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            User user = userService.findUserByEmail(auth.getName());
            return authCheck(user);
        } else {
            modelAndView.setViewName("index");
            return modelAndView;
        }

    }

    public ModelAndView authCheck(User user) {
        ModelAndView modelAndView = new ModelAndView();
        if (user.getRole().equals("admin")) {
            modelAndView.addObject("name", "Hi " + user.getFirstName() + "!");
            modelAndView.setViewName("admin");
            return modelAndView;
        } else {
            modelAndView.addObject("name", "Hi " + user.getFirstName() + "!");
            modelAndView.setViewName("userHomepage");
            return modelAndView;
        }
    }

    @GetMapping("/login")
    public ModelAndView login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView modelAndView = new ModelAndView();
        if ((auth instanceof AnonymousAuthenticationToken)) {
            modelAndView.setViewName("login");
            return modelAndView;
        } else {
            User user = userService.findUserByEmail(auth.getName());
            return authCheck(user);
        }
    }

    @GetMapping("create")
    public String newuser() {
        return "create";
    }

    @PostMapping("/create")
    public String create(User user, RedirectAttributes model) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        model.addAttribute("id", user.getId());
        return "redirect:/newuser/{id}";
    }


    @GetMapping("/warranty")
    public String warrantyForm() {
        return "warranty";
    }
    @GetMapping("/addProduct")
    public String addProduct() {
        return "createDevice";
    }

    @PostMapping("/product/save")
    public ModelAndView saveAndShow(
            @RequestParam(name = "id", defaultValue = "-1")
                    int id,
            @RequestParam(name = "productName", defaultValue = "NO_NAME")
                    String productName,
            @RequestParam(name = "deviceId", defaultValue = "NO_ID")
                    String deviceId,
            @RequestParam(name = "productId", defaultValue = "NO_ID")
                    String productId,
            @RequestParam(name = "productCat", defaultValue = "NO_PRODUCT_CAT")
                    String productCat) {


        Device device = deviceRepository.findById(id);
        if(productId.equals(deviceRepository.findByProductId(productId))){



        }
        else if (device == null) {
            device = new Device(productName, productId, productCat, deviceId);
        }else {
            device.setProductName(productName);
            device.setProductId(productId);
            device.setProductCat(productCat);
            device.setDeviceId(deviceId);
        }

        deviceRepository.save(device);

        ModelAndView mv = new ModelAndView("productList");
        mv.getModel().put("productList", deviceRepository.findAll());
        mv.getModel().put("device", device);

        return mv;
    }
    @GetMapping("/productList")
    public ModelAndView productList(){
        ModelAndView mv = new ModelAndView("productList");
        mv.getModel().put("productList", deviceRepository.findAll());
        return mv;
    }

    @GetMapping("/device/delete")
    public ModelAndView deleteProduct(@RequestParam(name="id", defaultValue = "0")int id) {
        deviceRepository.delete(deviceRepository.findById(id));
        ModelAndView mv = new ModelAndView("productList");
        mv.getModel().put("productList", deviceRepository.findAll());
        return mv;
    }



}
