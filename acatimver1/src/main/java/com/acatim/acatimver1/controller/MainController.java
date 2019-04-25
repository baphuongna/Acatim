package com.acatim.acatimver1.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@RequestMapping("/upload")
	public ModelAndView showUpload(@RequestParam(required = false, name = "message") String message) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", message);
		return new ModelAndView("testUpload");
	}
	
	@RequestMapping(value = { "/upload" }, method = RequestMethod.POST)
	public ModelAndView fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
		ModelAndView modelAndView = new ModelAndView();
//		String FTP_ADDRESS = "https://sg-files.hostinger.vn/";
//	    String LOGIN = "u179631086";
//	    String PSW = "lala123";
	    
	    String server = "srv179.main-hosting.eu";
		int port = 21;
		String user = "	u179631086";
		String pass = "lala123";
//	    FTPClient con = null;
	    FTPClient ftpClient = new FTPClient();
	    
	    try {
	    	
	    	InputStream inputStream =  new BufferedInputStream(file.getInputStream());
	    	System.out.println(file.getOriginalFilename() + "! " + inputStream.toString().substring(1));
			ftpClient.connect(server, port);
			ftpClient.login(user, pass);
			ftpClient.enterLocalPassiveMode();
			ftpClient.changeWorkingDirectory("/home/u179631086/domains/acatim.esy.es/public_html/Acatim");
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			// APPROACH #1: uploads first file using an InputStream
//			File firstLocalFile = new File("D:/Test/Projects.zip");
//
//			String firstRemoteFile = "Projects.zip";
//			InputStream inputStream = new FileInputStream(firstLocalFile);

			System.out.println("Start uploading first file");
			ftpClient.storeFile(file.getOriginalFilename(), inputStream);
			System.out.println(ftpClient.storeFile(file.getOriginalFilename(), inputStream));
//			inputStream.close();
//			System.out.println(done);
//			if (done) {
//				System.out.println("The first file is uploaded successfully.");
//				redirectAttributes.addFlashAttribute("message",
//	                    "You successfully uploaded " + file.getOriginalFilename() + "!");
//			}else {
//				System.out.println("Start uploading first file success");
//			}

			// APPROACH #2: uploads second file using an OutputStream
//			File secondLocalFile = new File("E:/Test/Report.doc");
//			String secondRemoteFile = "test/Report.doc";
//			inputStream = new FileInputStream(secondLocalFile);

//			System.out.println("Start uploading second file");
//			OutputStream outputStream = ftpClient.storeFileStream(secondRemoteFile);
//	        byte[] bytesIn = new byte[4096];
//	        int read = 0;
//
//	        while ((read = inputStream.read(bytesIn)) != -1) {
//	        	outputStream.write(bytesIn, 0, read);
//	        }
//	        inputStream.close();
//	        outputStream.close();

//	        boolean completed = ftpClient.completePendingCommand();
//			if (completed) {
//				System.out.println("The second file is uploaded successfully.");
//				 redirectAttributes.addFlashAttribute("message",
//		                    "You successfully uploaded " + file.getOriginalFilename() + "!");
//			}
			
		} catch (IOException ex) {
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
			redirectAttributes.addFlashAttribute("message",
	                "Could not upload " + file.getOriginalFilename() + "! " + file.getInputStream().toString().indexOf(0));
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
				redirectAttributes.addFlashAttribute("message",
		                "Could not upload " + file.getOriginalFilename() + "!");
			}
		}
	    
//	    try {

//	    	 System.out.println(file.getInputStream().toString());
//	        con = new FTPClient();
//	        System.out.println("CCCCCCCCCCCCCCC");
//	        con.connect(FTP_ADDRESS);
//	        System.out.println("AAAAAAAAAAAAAAA");
//	        if (con.login(LOGIN, PSW)) {
//	        	System.out.println("BBBBBBBBBBBBBBBBBBB");
//	            con.enterLocalPassiveMode(); // important!
//	            con.changeWorkingDirectory("/domains/acatim.tk/public_html/");
//	            con.setFileType(FTP.BINARY_FILE_TYPE);
//	            
//	            boolean result = con.storeFile(file.getOriginalFilename(), file.getInputStream());
//	            System.out.println(result);
//	            con.logout();
//	            con.disconnect();
//	            redirectAttributes.addFlashAttribute("message",
//	                    "You successfully uploaded " + file.getOriginalFilename() + "!");
//	        }
//	    } catch (Exception e) {
//	    	e.fillInStackTrace();
//	        redirectAttributes.addFlashAttribute("message",
//	                "Could not upload " + file.getOriginalFilename() + "!");
//	        
//	    }
	    modelAndView.setViewName("redirect:/upload");
	    return modelAndView;
	}

}
