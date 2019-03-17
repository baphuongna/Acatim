package com.acatim.acatimver1.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.acatim.acatimver1.model.Contact;
import com.acatim.acatimver1.service.ContactServiceImpl;

import javassist.NotFoundException;

@Controller
@RequestMapping(value = { "" })
public class ContactController {

	@Autowired
	private ContactServiceImpl contactService;

	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid @ModelAttribute("user") Contact data, BindingResult bindingResult)
			throws NotFoundException {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("data   " + data);
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String dateCurent = (String) dateFormat.format(date);
			data.setCreateDate(dateCurent);
			contactService.addContactInfo(data);
			System.out.println("gửi tin nhắn thành công");
		} catch (NotFoundException e) {
			System.out.println("error regitration");
			e.printStackTrace();
		}
		modelAndView.addObject("successMessage", "Tin nhắn đã được gửi");
		modelAndView.setViewName("contact");
		return modelAndView;
	}

}
