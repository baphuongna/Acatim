package com.acatim.acatimver1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.acatim.acatimver1.dao.ContactDAO;
import com.acatim.acatimver1.entity.Contact;
import com.acatim.acatimver1.entity.CountByDate;

import javassist.NotFoundException;
@Service
public class ContactServiceImpl implements ContactService{
	
	@Autowired
	private ContactDAO contactDao;
	
	public void addContactInfo(Contact contact) throws NotFoundException {
		this.contactDao.addContact(contact);
	}
	

	@Override
	public List<Contact> getAllContact() throws NotFoundException {
		return this.contactDao.getAllContact();
	}
	
	@Override
	public List<Contact> getAllContactPageable(Pageable pageable) throws NotFoundException {
		return this.contactDao.getAllContactPageable(pageable);
	}

	@Override
	public List<Contact> searchAllContactByUserName(Pageable pageable, String userName)
			throws NotFoundException {
		return this.contactDao.searchAllContactByUserName(pageable, userName);
	}

	@Override
	public List<Contact> searchAllContactByEmail(Pageable pageable, String email)
			throws NotFoundException {
		return this.contactDao.searchAllContactByEmail(pageable, email);
	}


	@Override
	public CountByDate countContactUsByDate() {
		return this.contactDao.countContactUsByDate();
	}

}
