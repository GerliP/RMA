package rma.project.rma.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import rma.project.rma.Entity.Device;
import rma.project.rma.Entity.User;
import rma.project.rma.Config.UserServiceImpl;
import rma.project.rma.service.DeviceService;
import rma.project.rma.service.UserService;


/**
 * Created by gerli on 20/02/2018.
 */
@Controller
public class HomeController {

    private final UserServiceImpl userServiceImpl;

    private final UserService userService;

    private final DeviceService deviceService;

    @Autowired
    public HomeController(UserServiceImpl userServiceImpl, UserService userService, DeviceService deviceService) {
        this.userServiceImpl = userServiceImpl;
        this.userService = userService;
        this.deviceService = deviceService;
    }

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            User user = userServiceImpl.findUserByEmail(auth.getName());
            return authCheck(user);
        } else {
            modelAndView.setViewName("index");
            return modelAndView;
        }

    }

    private ModelAndView authCheck(User user) {
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
            User user = userServiceImpl.findUserByEmail(auth.getName());
            return authCheck(user);
        }
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
        System.out.println("before find");
        Device device = deviceService.findOne(id);
        System.out.println("after find");
        if (device.getDeviceId() == "NO_ID") {
            device = new Device(productName, productId, productCat, deviceId);
        } else {
            device.setProductName(productName);
            device.setProductId(productId);
            device.setProductCat(productCat);
            device.setDeviceId(deviceId);
        }

        deviceService.create(device);
        ModelAndView mv = new ModelAndView("productList");
        mv.getModel().put("productList", deviceService.findAll());
        mv.getModel().put("device", device);

        return mv;
    }

    @GetMapping("/products")
    public ModelAndView productList() {
        ModelAndView mv = new ModelAndView("productList");
        mv.getModel().put("productList", deviceService.findAll());
        return mv;
    }

    @GetMapping("/device/delete")
    public ModelAndView deleteProduct(@RequestParam(name = "id", defaultValue = "0") int id) {
        deviceService.delete(id);
        ModelAndView mv = new ModelAndView("productList");
        mv.getModel().put("productList", deviceService.findAll());
        return mv;
    }

    @GetMapping("/users")
    public ModelAndView users() {
        ModelAndView mv = new ModelAndView("users");
        mv.getModel().put("users", userService.findAll());
        return mv;
    }

    @GetMapping("/user/delete")
    public ModelAndView deleteUser(@RequestParam(name = "id", defaultValue = "0") int id) {
        userService.delete(id);
        ModelAndView mv = new ModelAndView("users");
        mv.getModel().put("users", userService.findAll());
        return mv;
    }

    @GetMapping("/user/edit")
    public ModelAndView editUser(@RequestParam(name = "id", defaultValue = "0") int id, @ModelAttribute User user) {
        userService.update(id, user);
        ModelAndView mv = new ModelAndView("users");
        mv.getModel().put("users", userService.findAll());
        return mv;
    }

}
