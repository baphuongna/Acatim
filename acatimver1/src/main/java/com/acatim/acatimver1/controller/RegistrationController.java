package com.acatim.acatimver1.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.acatim.acatimver1.entity.ConfirmEmail;
import com.acatim.acatimver1.entity.ObjectUser;
import com.acatim.acatimver1.entity.Student;
import com.acatim.acatimver1.entity.StudyCenter;
import com.acatim.acatimver1.entity.Teacher;
import com.acatim.acatimver1.entity.UserModel;
import com.acatim.acatimver1.format.DateFormat;
import com.acatim.acatimver1.service.UserInfoService;

import javassist.NotFoundException;

@Controller
@RequestMapping(value = { "" })
public class RegistrationController {

	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
    public JavaMailSender emailSender;
	
	private DateFormat dateformat = new DateFormat();

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		UserModel user = new UserModel();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid @ModelAttribute("user") ObjectUser data, BindingResult bindingResult, RedirectAttributes redirectAttributes)
			throws NotFoundException {
		ModelAndView modelAndView = new ModelAndView();
		SimpleMailMessage message = new SimpleMailMessage();
		boolean userExists = userInfoService.checkUserExist(data.getUserName());
		if (userExists == true) {
			bindingResult.rejectValue("userName", "error.user",
					"Tên Tài khoản này đã tồn tại, vui lòng nhập một Tên Tài khoản khác !");
		}
		
		UserModel checkEmail = userInfoService.loadUserbyEmail(data.getEmail());
		
		if(checkEmail != null) {
			bindingResult.rejectValue("email", "error.email", "Eamil này đã tồn tại, vui lòng nhập một địa chỉ Eamil khác !");
		}
		
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("erorrMessage", "Bạn đã nhập sai một số thông tin, vui lòng kiểm tra lại");
			modelAndView.setViewName("registration");
		} else {
			try {
				data.setCreateDate(dateformat.currentDate());
				if(data.getDob() == null || data.getDob() != null && data.getDob().trim().length() == 0) {
					data.setDob(dateformat.currentDate());
				}
				
				UserModel user = new UserModel(data.getUserName(), data.getRole_id(), data.getFullName(),
						data.getEmail(), data.getPassword(), data.getCreateDate(), data.getPhone(), data.getAddress(),
						false);
				String key = dateformat.RandomKeyConfirm();
				String content = null;
				if (data.getRole_id() == 1) {
					Student newStudent = new Student(data.getUserName(), data.getCreateDate(), data.isGender());
					System.out.println("student" + newStudent);
					userInfoService.addUserInfo(user);
					userInfoService.addStudentInfo(newStudent);
					System.out.println("học sinh thanh cong");
					content = "Xin Chào, \r\n \r\n"
				     		+ "Chúng tôi cần xác minh Email đúng hay không. Vui lòng bạn xác nhận Email và bắt đầu sử dụng tài khoản Website chúng tôi.\r\n"
				     		+ "Click vào địa Chỉ sau : http://acatim.online/confirm-account?userName="+ data.getUserName()+"&email="+ user.getEmail() +"&key=" + key + "\r\n \r\n"
				     		+ "ACATIM.";
				}
				if (data.getRole_id() == 2) {
					Teacher newTeacher = new Teacher(data.getUserName(), data.getDob(), data.isGender(),
							data.getDescription(), 0);
					userInfoService.addUserInfo(user);
					userInfoService.addTeacherInfo(newTeacher);
					System.out.println("giáo vien thanh cong");
					content = "Xin chào,  \r\n \r\n"
							+ "Cảm ơn bạn đã dùng website của chúng tôi.  \r\n \r\n"
							+ "Để hoàn tất đăng ký cho bạn lên website Acatim, bạn gửi thông tin xác nhận đến email acatimdev@gmail.com bao gồm:\r\n \r\n"
							+ "- Hình ảnh chứng minh thư \r\n \r\n"
							+ "- Hình ảnh chứng thực mình là Giáo Viên.\r\n \r\n"
							+ "Thông tin sẽ được chúng tôi tiếp nhận và xử lý trong vòng 72h làm việc.\r\n \r\n"
							+ "ACATIM.";
				}
				if (data.getRole_id() == 3) {
					StudyCenter newStudyCenter = new StudyCenter(data.getUserName(), data.getDescription(), 0);
					userInfoService.addUserInfo(user);
					userInfoService.addStudyCenterInfo(newStudyCenter);
					System.out.println("trung tam thanh cong");
					content = "Xin chào,  \r\n \r\n"
							+ "Cảm ơn bạn đã dùng website của chúng tôi.  \r\n \r\n"
							+ "Để hoàn tất đăng ký cho bạn lên website Acatim, bạn gửi thông tin xác nhận đến email acatimdev@gmail.com bao gồm:\r\n \r\n"
							+ "- Hình ảnh chứng minh thư \r\n \r\n"
							+ "- Hình ảnh chứng thực tài khoản này là Trung Tâm.\r\n \r\n"
							+ "Thông tin sẽ được chúng tôi tiếp nhận và xử lý trong vòng 72h làm việc.\r\n \r\n"
							+ "ACATIM.";
				}
				
				
				
				if(userInfoService.addConfirm(new ConfirmEmail(data.getUserName(), key, false))) {
					
					message.setTo(user.getEmail());
				    message.setSubject("Xác Nhận Đăng Ký Tài Khoản Acatim");
				    message.setText(content);
			        
				     this.emailSender.send(message);
				     redirectAttributes.addAttribute("message", "Chúc mừng bạn đã đăng kí thành công tài khoản. Để Hoàn tất phần đăng ký bạn vào Email "+ data.getUserName() +" để xác minh.");
				}else {
					redirectAttributes.addAttribute("message", "Gửi mã xác nhận không thành công, vui lòng kiểm tra lại hoặc liên hệ với chúng tôi!");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				modelAndView.addObject("erorrMessage", "đăng kí thất bại, vui lòng kiểm tra lại!");
				modelAndView.setViewName("registration");
				return modelAndView;
			}
			
			modelAndView.setViewName("redirect:/active-acc");

		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/active-acc", method = RequestMethod.GET)
	public ModelAndView sendVerification(@RequestParam("message") String message) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", message);
		modelAndView.setViewName("active-acc");
		return modelAndView;
	}
	
	@RequestMapping(value = "/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView verification(@RequestParam("userName") String userName, @RequestParam("email") String email, @RequestParam("key") String key, RedirectAttributes redirectAttributes) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		UserModel user = userInfoService.findAccConfirm(userName, email);

		if(user != null) {
			if(user.getConfirmEmail() != null && key.equals(user.getConfirmEmail().getKey())) {
				if(user.getConfirmEmail().isStatus() == false) {
					userInfoService.unlockUser(userName);
					userInfoService.updateConfirm(userName, true);
					redirectAttributes.addAttribute("message", "Chúc mừng bạn đã xác minh Email thành công tài khoản. Hiện tại bạn có thể đăng nhập vào website Acatim.");
				}else {
					redirectAttributes.addAttribute("message", "Bạn đã xác minh Email tài khoản. Bạn có thể đăng nhập vào website Acatim.");
				}
			}else {
				redirectAttributes.addAttribute("message", "mã xác nhận không Chính xác, vui lòng kiểm tra lại hoặc liên hệ với chúng tôi!");
			}
		}else {
			redirectAttributes.addAttribute("message", "xác nhận không thành công, vui lòng kiểm tra lại hoặc liên hệ với chúng tôi!");
		}
		modelAndView.setViewName("redirect:/active-acc");
		return modelAndView;
	}


}
