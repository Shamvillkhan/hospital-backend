package com.hospital.management.service;

import java.util.List;



import com.hospital.management.entity.ContactUs;

public interface ContactUsService {
	
	List<ContactUs> getAllContacts();

	ContactUs addContact(ContactUs contactUs);

	void deleteContact(long id);

	ContactUs getContactById(long id);

	ContactUs updateContact(ContactUs contactUs);
}
