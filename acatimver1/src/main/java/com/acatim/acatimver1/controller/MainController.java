package com.acatim.acatimver1.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import com.acatim.acatimver1.service.UserInfoService;
import com.acatim.acatimver1.utils.WebUtils;

@Controller
@RequestMapping(value = {""})
public class MainController {

	@Autowired
	private UserInfoService userInfoService;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public ModelAndView index(Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();

			String userRole = WebUtils.toString(loginedUser);

			modelAndView.addObject("userRole", userRole);
		}

		try {
			modelAndView.addObject("numberOfTeacher", userInfoService.countTeacher());
			modelAndView.addObject("numberOfStudyCenter", userInfoService.countStudyCenter());
			modelAndView.addObject("numberOfStudent", userInfoService.countStudent());
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("index");
		return modelAndView;
	}

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error(Model model) {
        return "error";
    }
    
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "not found")
	public ModelAndView notFoundException(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("404");
		return modelAndView;
	}

	@RequestMapping("/password-recovery")
	public String showAllCourse(Model model) {
		return "password-recovery";
	}
	
	
	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public String loginForm() {
		return "loginPage";
	}


	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal) {

		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();

			String userInfo = WebUtils.toString(loginedUser);

			model.addAttribute("userInfo", userInfo);

			String message = "Xin Chào " + principal.getName() //
					+ "<br> Bạn không được quyền vào trang này !";
			model.addAttribute("message", message);

		}

		return "403Page";
	}

	@RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
	public String logoutSuccessfulPage(Model model) {
		model.addAttribute("title", "Logout");
		return "redirect:/";
	}

	@RequestMapping(value = { "/contact" }, method = RequestMethod.GET)
	public ModelAndView contact() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("contact");
		return modelAndView;
	}

}
