package com.hospital.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.management.entity.ContactUs;
import com.hospital.management.service.ContactUsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("contactus")
public class ContactUsController {

	@Autowired
	private ContactUsService contactUsService;

	public ContactUsController(ContactUsService contactUsService) {
		this.contactUsService = contactUsService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> createContact(@Valid @RequestBody ContactUs contact) {
		try {
			ContactUs savedContact = contactUsService.addContact(contact);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedContact);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating contact: " + e.getMessage());
		}
	}

	@PutMapping("/update")
	public ResponseEntity<ContactUs> updateBlog(@Valid @RequestBody ContactUs contactUs) {
		ContactUs updatedContactUs = contactUsService.updateContact(contactUs);
		return ResponseEntity.ok(updatedContactUs);
	}


	@GetMapping("/getAll")
	public ResponseEntity<List<ContactUs>> getAllContacts() {
		List<ContactUs> contacts = contactUsService.getAllContacts();
		return ResponseEntity.ok(contacts);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<?> getContactById(@PathVariable Long id) {
		try {
			ContactUs contact = contactUsService.getContactById(id);
			return ResponseEntity.ok(contact);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found with id: " + id);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteContact(@PathVariable Long id) {
		try {
			contactUsService.deleteContact(id);
			return ResponseEntity.ok("Contact deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error deleting contact: " + e.getMessage());
		}
	}
}
