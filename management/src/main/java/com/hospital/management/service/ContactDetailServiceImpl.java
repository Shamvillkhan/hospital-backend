package com.hospital.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.management.entity.ContactDetail;
import com.hospital.management.repository.ContactDetailRepository;

@Service
public class ContactDetailServiceImpl implements ContactDetailService {

    @Autowired
    private ContactDetailRepository contactDetailRepository;

    @Override
    public List<ContactDetail> getAllContactDetail() {
        return contactDetailRepository.findAll();
    }

    @Override
    public ContactDetail addContactDetail(ContactDetail contactDetail) {
        return contactDetailRepository.save(contactDetail);
    }

    @Override
    public void deleteContactDetail(long id) {
        contactDetailRepository.deleteById(id);
    }

    @Override
    public ContactDetail getContactDetailById(long id) {
        Optional<ContactDetail> optional = contactDetailRepository.findById(id);
        return optional.orElse(null); // or throw custom exception
    }

    @Override
    public ContactDetail updateContactDetail(ContactDetail contactDetail) {
        // Save will update if ID already exists
        return contactDetailRepository.save(contactDetail);
    }

    // ✅ Custom method: fetch only active contact details
    public List<ContactDetail> getActiveContactDetails() {
        return contactDetailRepository.findAllByActiveTrue();
    }
}
