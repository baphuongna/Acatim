package com.acatim.acatimver1.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.acatim.acatimver1.entity.Images;
import com.acatim.acatimver1.entity.SearchValue;
import com.acatim.acatimver1.format.DateFormat;
import com.acatim.acatimver1.service.AmazonClient;
import com.acatim.acatimver1.service.ImagesService;
import com.acatim.acatimver1.service.PageableService;
import com.acatim.acatimver1.service.PageableServiceImpl;
//import com.acatim.acatimver1.service.UserInfoService;

import javassist.NotFoundException;

@Controller
@RequestMapping(value = { "" })
public class ImagesController {

//	@Autowired
//	private UserInfoService userInfoService;

	@Autowired
	private ImagesService imagesService;

	private PageableService pageableService;

	private DateFormat dateformat = new DateFormat();

	private AmazonClient amazonClient;

	@Autowired
	ImagesController(AmazonClient amazonClient) {
		this.amazonClient = amazonClient;
	}

	@RequestMapping(value = "/manager-images", method = RequestMethod.GET)
	public ModelAndView managerCourse(@RequestParam(required = false, name = "page") String page, Principal principal,
			@ModelAttribute("searchValue") SearchValue search) throws NotFoundException {
		ModelAndView modelAndView = new ModelAndView();
		String userName = null;
		if (principal != null) {
			userName = principal.getName();
		} else {
			modelAndView.setViewName("redirect:/index");
			return modelAndView;
		}

		if (page == null) {
			page = 1 + "";
		}

		try {
			int currentPage = Integer.parseInt(page);

			if (currentPage < 1) {
				currentPage = 1;
			}

			search.setUserName(userName);

			int total = imagesService.countImagesByUserName(userName);

			pageableService = new PageableServiceImpl(total, total, currentPage, null);

			List<Images> images = imagesService.getImagesByUserName(pageableService, userName);

			modelAndView.addObject("images", images);

			modelAndView.addObject("totalPages", pageableService.listPage());
			modelAndView.addObject("currentPage", currentPage);
			modelAndView.addObject("hasPrevious", pageableService.hasPrevious());
			modelAndView.addObject("hasNext", pageableService.hasNext());
			modelAndView.addObject("previous", pageableService.previous());
			modelAndView.addObject("next", pageableService.next());
			modelAndView.addObject("last", pageableService.last());
			modelAndView.addObject("first", pageableService.first());

			modelAndView.addObject("searchValue", search);
		} catch (Exception e) {
			e.printStackTrace();
		}

		modelAndView.setViewName("manager-image");

		return modelAndView;
	}

	@RequestMapping(value = { "manager-images" }, method = RequestMethod.POST)
	public ModelAndView searchCourses(@ModelAttribute("searchValue") SearchValue search,
			RedirectAttributes redirectAttributes) {

		ModelAndView modelAndView = new ModelAndView();

		redirectAttributes.addFlashAttribute("searchValue", search);
		modelAndView.setViewName("redirect:/manager-images");
		return modelAndView;
	}

	@RequestMapping("/add-image")
	public ModelAndView showUpload(@RequestParam(required = false, name = "message") String message) {
		ModelAndView modelAndView = new ModelAndView();
		Images images = new Images();
		modelAndView.addObject("images", images);
		modelAndView.addObject("message", message);
		return new ModelAndView("add-image");
	}

	@RequestMapping(value = { "/add-image" }, method = RequestMethod.POST)
	public ModelAndView fileUpload(@RequestPart(value = "file") MultipartFile file,
			RedirectAttributes redirectAttributes,  @ModelAttribute("description") String description,
			Principal principal) throws IOException {
		ModelAndView modelAndView = new ModelAndView();

		String curentUserName = null;
		if (principal != null) {
			curentUserName = principal.getName();
		}
		
		if(file.getSize() > 5242880) {//5mb
			redirectAttributes.addFlashAttribute("message",
	                "Hình ảnh tải lên không được lớn hơn 5mb, vui lòng chọn ảnh khác!");
			modelAndView.setViewName("redirect:/add-image");
			return modelAndView;
		}
		
		String image = this.amazonClient.uploadFile(curentUserName+"/images" ,file);
		
		try {
			Images images = new Images();
			images.setUserName(curentUserName);
			images.setDescription(description);
			images.setLinkimage(image);
			images.setActive(true);
			images.setCreateDate(dateformat.currentDate());
			System.out.println(file.getOriginalFilename() + " " +images);
			imagesService.addImage(images);
			redirectAttributes.addFlashAttribute("message",
	                "Upload Successful file " + file.getOriginalFilename() + "!");
		}catch (Exception e) {
			e.fillInStackTrace();
			redirectAttributes.addFlashAttribute("message",
	                "Could not upload " + file.getOriginalFilename() + "!");
		}
		
		modelAndView.setViewName("redirect:/add-image");
		return modelAndView;
	}
	
	@RequestMapping(value = { "blockimage" }, method = RequestMethod.GET)
	public ModelAndView blockImage(@RequestParam("id") String id, Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			
			imagesService.activeImages(id, false);

		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("redirect:/manager-images");
		return modelAndView;
	}

	@RequestMapping(value = { "unlockimage" }, method = RequestMethod.GET)
	public ModelAndView unlockImage(@RequestParam("id") String id, Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			imagesService.activeImages(id, true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("redirect:/manager-images");
		return modelAndView;
	}
	
	@RequestMapping(value = { "deleteimage" }, method = RequestMethod.GET)
	public ModelAndView deleteImage(@RequestParam("id") String id, Principal principal) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			
			String curentUserName = null;
			if (principal != null) {
				curentUserName = principal.getName();
			}
			
			Images image = imagesService.getImagesById(id);
			if(image != null) {
				String imageUrl = image.getLinkimage();
				imagesService.deleteImages(id);
				this.amazonClient.deleteFileFromS3Bucket(curentUserName+"/images", imageUrl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("redirect:/manager-images");
		return modelAndView;
	}

}
