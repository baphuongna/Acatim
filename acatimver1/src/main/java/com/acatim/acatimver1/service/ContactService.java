package com.acatim.acatimver1.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.acatim.acatimver1.entity.Contact;
import com.acatim.acatimver1.entity.CountByDate;

import javassist.NotFoundException;

public interface ContactService {
	void addContactInfo(Contact contact) throws NotFoundException;
	
	List<Contact> getAllContact() throws NotFoundException;
	List<Contact> getAllContactPageable(Pageable pageable) throws NotFoundException;
	List<Contact> searchAllContactByUserName(Pageable pageable, String userName) throws NotFoundException;
	List<Contact> searchAllContactByEmail(Pageable pageable, String email) throws NotFoundException;
	
	CountByDate countContactUsByDate();
}
