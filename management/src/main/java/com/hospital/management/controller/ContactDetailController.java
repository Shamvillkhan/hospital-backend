package com.hospital.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hospital.management.entity.ContactDetail;
import com.hospital.management.service.ContactDetailServiceImpl;

@RestController
@RequestMapping("/contactdetail")
public class ContactDetailController {

    @Autowired
    private ContactDetailServiceImpl contactDetailService;


    @GetMapping("/getAll")
    public ResponseEntity<List<ContactDetail>> getAllContactDetails() {
        return ResponseEntity.ok(contactDetailService.getAllContactDetail());
    }

  
    @GetMapping("/get/{id}")
    public ResponseEntity<ContactDetail> getContactDetailById(@PathVariable long id) {
        ContactDetail contact = contactDetailService.getContactDetailById(id);
        if (contact == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contact);
    }

  
    @PostMapping("/add")
    public ResponseEntity<ContactDetail> addContactDetail(@RequestBody ContactDetail contactDetail) {
        return ResponseEntity.ok(contactDetailService.addContactDetail(contactDetail));
    }

   
    @PutMapping("/update")
    public ResponseEntity<ContactDetail> updateContactDetail( 
                                                             @RequestBody ContactDetail contactDetail) {
     
        return ResponseEntity.ok(contactDetailService.updateContactDetail(contactDetail));
    }

 
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteContactDetail(@PathVariable long id) {
        contactDetailService.deleteContactDetail(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/active")
    public ResponseEntity<List<ContactDetail>> getActiveContactDetails() {
        return ResponseEntity.ok(contactDetailService.getActiveContactDetails());
    }
}
