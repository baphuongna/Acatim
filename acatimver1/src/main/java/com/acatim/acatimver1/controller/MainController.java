package com.acatim.acatimver1.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.acatim.acatimver1.utils.WebUtils;

@Controller
public class MainController {
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
 
//    @RequestMapping(value = "/admin", method = RequestMethod.GET)
//    public String adminPage(Model model, Principal principal) {
//         
//        User loginedUser = (User) ((Authentication) principal).getPrincipal();
// 
//        String userInfo = WebUtils.toString(loginedUser);
//        model.addAttribute("userInfo", userInfo);
//         
//        return "adminPage";
//    }
// 
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String loginPage(Model model) {
// 
//        return "loginPage";
//    }
 
//    @RequestMapping(value = "/index", method = RequestMethod.GET)
//    public String logoutSuccessfulPage(Model model) {
//        //model.addAttribute("title", "Logout");
//        return "index";
//    }
 
    @RequestMapping(value = "/profileStudent", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {
 
        // Sau khi user login thanh cong se co principal
        String userName = principal.getName();
 
       // System.out.println("User Name: " + userName);
 
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = WebUtils.toString(loginedUser);
       // model.addAttribute("userInfo", userInfo);
 
        return "profileS";
    }
 
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
 
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
            String userInfo = WebUtils.toString(loginedUser);
 
           // model.addAttribute("userInfo", userInfo);
 
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
 
        }
 
        return "403Page";
    }
    

    @RequestMapping(value={"/about"}, method = RequestMethod.GET)
    public ModelAndView about(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("about");
        return modelAndView;
    }
    
    @RequestMapping(value={"/course"}, method = RequestMethod.GET)
    public ModelAndView course(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("course");
        return modelAndView;
    }
    
    @RequestMapping(value={"/teacher"}, method = RequestMethod.GET)
    public ModelAndView teacher(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("teacher");
        return modelAndView;
    }
    
    @RequestMapping(value={"/blog"}, method = RequestMethod.GET)
    public ModelAndView blog(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("blog");
        return modelAndView;
    }
    
    @RequestMapping(value={"/event"}, method = RequestMethod.GET)
    public ModelAndView event(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("event");
        return modelAndView;
    }
    
    @RequestMapping(value={"/contact"}, method = RequestMethod.GET)
    public ModelAndView contact(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("contact");
        return modelAndView;
    }
   
}
