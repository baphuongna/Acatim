package com.acatim.acatimver1.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.acatim.acatimver1.entity.SearchValue;
import com.acatim.acatimver1.entity.UserModel;
import com.acatim.acatimver1.format.DateFormat;
import com.acatim.acatimver1.service.UserInfoService;
import com.acatim.acatimver1.utils.WebUtils;

import javassist.NotFoundException;

@Controller
@RequestMapping(value = {""})
public class MainController {

	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
    public JavaMailSender emailSender;
	
	private DateFormat dateformat = new DateFormat();

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
	public ModelAndView forgotPassword(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("searchValue", new SearchValue());
		modelAndView.setViewName("password-recovery");
		return modelAndView;
	}
	
	@RequestMapping(value = "/password-recovery", method = RequestMethod.POST)
	public ModelAndView getNewPassword(@ModelAttribute("searchValue") SearchValue search) {
		// Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();
        ModelAndView modelAndView = new ModelAndView();
        UserModel user = null;
        if(search.getSearch() != null) {
        	try {
				user = userInfoService.loadUserbyEmail(search.getSearch());
				
				if(user == null) {
					modelAndView.addObject("error", "Nhập Email không đúng hoặc Email chưa được đăng Ký!");
					modelAndView.setViewName("password-recovery");
					return modelAndView;
				}else {
					String newPass = dateformat.RandomPassword();
					
					userInfoService.changePassword(user.getUserName(), newPass);
					
					message.setTo(user.getEmail());
				    message.setSubject("Yêu cầu thay đổi mật khẩu Acatim");
				    message.setText("Xin Chào,"
				     		+ "<br> Để thiết lập lại mật khẩu tài khoản Ubisoft của bạn xin vui lòng bấm vào địa chỉ sau "
				     		+ "<br> Mật Khẩu Mới : <p>" + newPass + "</p>"
				     		+ "<br> ACATIM.");
				     // Send Message!
				     this.emailSender.send(message);
				     modelAndView.setViewName("send-passwod-success");
				}
			} catch (NotFoundException e) {
				modelAndView.addObject("error", "Yêu cầu thay đổi mật khẩu không thành công!");
				modelAndView.setViewName("password-recovery");
				return modelAndView;
			}
        }
		return modelAndView;
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
