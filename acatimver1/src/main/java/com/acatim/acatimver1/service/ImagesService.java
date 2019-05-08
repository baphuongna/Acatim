package com.acatim.acatimver1.service;

import java.util.List;

import com.acatim.acatimver1.entity.Images;

public interface ImagesService {
	int countImages();
	
	int countImagesByUserName(String userName);
	
	List<Images> getImagesByUserName(PageableService pageable ,String userName);
	
	Images getImagesById(String id);
	
	boolean addImage(Images images);
	
	boolean updateImages(Images images);
	 
	boolean activeImages(String id, boolean active);
	 
	boolean deleteImages(String id);
	
}
