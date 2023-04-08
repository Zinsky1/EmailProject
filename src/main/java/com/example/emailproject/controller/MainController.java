package com.example.emailproject.controller;

import com.example.emailproject.Service.UserServiceImp;
import com.example.emailproject.dao.RoleDao;
import com.example.emailproject.model.Role;
import com.example.emailproject.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.Arrays;
import java.util.Set;


@Controller
public class MainController {

    private final UserServiceImp userService;
    private final RoleDao roleDao;

    public MainController(UserServiceImp userService, RoleDao roleDao) {
        this.userService = userService;
        this.roleDao = roleDao;
    }

    @GetMapping("/login")
    public String login() {
        return "login2";
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout2";
    }

    @GetMapping()
    public String auth() {
        return "home";
    }

    @GetMapping("/user/{email}")
    public String userPage(@PathVariable("email") String email, Model model, Principal principal) {
        email = principal.getName();
        model.addAttribute("user",
                userService.findByEmail(email));
        //model.addAttribute("getUser", SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        return "user";
    }

    @GetMapping("/admin")
    public String adminPage(Model model, Principal principal) {
        model.addAttribute("admin", userService.findByEmail(principal.getName()));
        model.addAttribute("users", userService.listUsers());
        return "admin";
    }





    /////////////////////////////////////////////

    //testing space
    @GetMapping("/admin/try")
    public String tryPage(
            Model model,
            Principal principal,
            @ModelAttribute("userToSave") User userToSave) {
        model.addAttribute("admin", userService.findByEmail(principal.getName()));
        model.addAttribute("users", userService.listUsers());
        //model.addAttribute("adminRole", Set.of(Role.adminRole));
        //model.addAttribute("userRole", Set.of(Role.userRole.getName()));
        model.addAttribute("roles", roleDao.allRoles());


        return "test";
    }

    @PostMapping("/admin/try")
    public String adding(User userToSave) {
        userService.add(userToSave);

        return "redirect:/admin";
    }


    @DeleteMapping("/admin/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
                userService.deleteUser(id);
                return "redirect:/admin";
    }


    @PatchMapping("/admin/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }


    @GetMapping("/admin/get/{email}")
    public String getUser(@PathVariable("email") String email, Model model, Principal principal) {
        model.addAttribute("emailUser", userService.findByEmail(email));
        model.addAttribute("adminData", userService.findByEmail(principal.getName()));
        return "get";
    }



//    @GetMapping("/admin/{id}")
//    public String edit(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("userToUpdate", userService.getUser(id));
//        return "try";
//
//    }

//    @GetMapping("/admin/user/{email}")
//    public String getUserPage(@PathVariable("email") String email, Model model) {
//        model.addAttribute("userToShow", userService.findByEmail(email));
//        return "get";
//    }

//    @GetMapping("/admin/new")
//    public String newUserPage(@ModelAttribute("user") User user) {
//        return "admin/new";
//    }




//    @GetMapping("/admin/{id}/edit")
//    public String updateUserPage(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("user", userRepository.findById(id));
//
//        return "admin/update";
//    }



//    @DeleteMapping("/admin/{id}")
//    public String deleteUser(@PathVariable("id") Long id) {
//        userRepository.deleteById(id);
//        return "redirect:/admin";
//    }

}
