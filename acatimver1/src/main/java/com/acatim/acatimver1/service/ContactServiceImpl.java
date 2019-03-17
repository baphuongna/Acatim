package com.acatim.acatimver1.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.acatim.acatimver1.dao.ContactDAO;
import com.acatim.acatimver1.model.Contact;

import javassist.NotFoundException;

public class ContactServiceImpl {
	
	@Autowired
	private ContactDAO contactDao;
	
	public void addContactInfo(Contact contact) throws NotFoundException {
		this.contactDao.addContact(contact);

	}

}
