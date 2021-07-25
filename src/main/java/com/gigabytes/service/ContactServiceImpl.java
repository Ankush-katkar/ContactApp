package com.gigabytes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gigabytes.entity.Contact;
import com.gigabytes.repository.ContactRepository;


@Service
public class ContactServiceImpl implements ContactService {
	
	
	
	private ContactRepository contactRepo;
	
	public ContactServiceImpl(ContactRepository contactRepo) {
		this.contactRepo=contactRepo;
	}
	@Override
	public boolean saveContact(Contact contact) {
		
		Contact savedContact= contactRepo.save(contact);
		
		return savedContact.getContactId()!= null;
	}
	@Override
	public List<Contact> getAllContacts() {
		
		return contactRepo.findAll();
		
	}
	@Override
	public Contact getContactById(Integer contactId) {
		
		Optional<Contact> findById =contactRepo.findById(contactId);
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
	}
	@Override
	public boolean deleteContactById(Integer contactId) {
		try {
			contactRepo.deleteById(contactId);
			return true;	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	

}
