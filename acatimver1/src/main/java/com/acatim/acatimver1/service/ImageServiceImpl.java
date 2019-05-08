package com.acatim.acatimver1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acatim.acatimver1.dao.ImagesDAO;
import com.acatim.acatimver1.entity.Images;

@Service
public class ImageServiceImpl implements ImagesService {

	@Autowired
	private ImagesDAO imagesDAO;
	
	@Override
	public int countImages() {
		return this.imagesDAO.countImages();
	}

	@Override
	public int countImagesByUserName(String userName) {
		return this.imagesDAO.countImagesByUserName(userName);
	}

	@Override
	public List<Images> getImagesByUserName(PageableService pageable, String userName) {
		return this.imagesDAO.getImagesByUserName(pageable, userName);
	}

	@Override
	public Images getImagesById(String id) {
		return this.imagesDAO.getImagesById(id);
	}

	@Override
	public boolean addImage(Images images) {
		return this.imagesDAO.addImage(images);
	}

	@Override
	public boolean updateImages(Images images) {
		return this.imagesDAO.updateImages(images);
	}

	@Override
	public boolean activeImages(String id, boolean active) {
		return this.imagesDAO.activeImages(id, active);
	}

	@Override
	public boolean deleteImages(String id) {
		return this.imagesDAO.deleteImages(id);
	}

}
