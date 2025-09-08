package com.hospital.management.service;

import java.util.List;

import com.hospital.management.entity.ContactDetail;

public interface ContactDetailService {
	
	List<ContactDetail> getAllContactDetail();

	ContactDetail addContactDetail(ContactDetail contacDetail);

	void deleteContactDetail(long id);

	ContactDetail getContactDetailById(long id);

	ContactDetail updateContactDetail(ContactDetail contacDetail);
}
