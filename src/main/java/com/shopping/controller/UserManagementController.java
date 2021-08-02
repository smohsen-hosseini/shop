

package com.shopping.controller;

        import com.shopping.model.Product;
        import com.shopping.model.User;
        import com.shopping.service.ProductService;
        import com.shopping.service.UserService;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Qualifier;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.validation.BindingResult;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.ModelAttribute;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.PostMapping;

        import java.security.Principal;

@Controller
public class UserManagementController {
    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserManagementController.class);


    @Autowired
    public UserManagementController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String userList(Model model){
        var users = userService.findAll();

        if (users != null) {
            model.addAttribute("users", users);
        }else {
            return "error/404";
        }

        return "userManagement" ;
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") long userId, Model model){
        User user = userService.findById(userId);

        if (user != null){
            model.addAttribute("user", user);
            model.addAttribute("method", "edit");
            return "user";
        }else {
            return "error/404";
        }
    }

    @PostMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") long Id, @ModelAttribute("user") User user, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            logger.error(String.valueOf(bindingResult.getFieldError()));
            model.addAttribute("method", "edit");
            return "userManagement";
        }
        userService.edit(Id, user.getBlocked());
        logger.debug(String.format("user with id: %s has been successfully edited.", Id));

        return "redirect:/users";
    }


}
