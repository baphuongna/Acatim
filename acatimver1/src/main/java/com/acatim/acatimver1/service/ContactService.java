package com.acatim.acatimver1.service;

import com.acatim.acatimver1.entity.Contact;

import javassist.NotFoundException;

public interface ContactService {
	void addContactInfo(Contact contact) throws NotFoundException;
}
