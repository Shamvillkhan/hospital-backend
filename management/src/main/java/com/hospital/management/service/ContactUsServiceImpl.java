package com.hospital.management.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hospital.management.entity.ContactUs;
import com.hospital.management.repository.ContactUsRepository;

@Service
public class ContactUsServiceImpl implements ContactUsService {

	private ContactUsRepository contactUsRepository;

	public ContactUsServiceImpl(ContactUsRepository contactUsRepository) {
		this.contactUsRepository = contactUsRepository;
	}

	@Override
	public ContactUs addContact(ContactUs contact) {
		contact.setCreatedAt(LocalDateTime.now());
		return contactUsRepository.save(contact);
	}

	@Override
	public List<ContactUs> getAllContacts() {
		return contactUsRepository.findAll();
	}

	@Override
	public ContactUs getContactById(long id) {
		return contactUsRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Contact not found with id " + id));
	}

	@Override
	public void deleteContact(long id) {
		if (!contactUsRepository.existsById(id)) {
			throw new RuntimeException("Contact not found with id " + id);
		}
		contactUsRepository.deleteById(id);
	}

	@Override
	public ContactUs updateContact(ContactUs contactUs) {

		return contactUsRepository.save(contactUs);
	}

}
