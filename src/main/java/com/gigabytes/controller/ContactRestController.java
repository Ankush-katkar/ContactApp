package com.gigabytes.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gigabytes.entity.Contact;
import com.gigabytes.props.AppProperties;
import com.gigabytes.service.ContactService;

@RestController
@RequestMapping("/api/contact")
public class ContactRestController {
	
	Logger logger = LoggerFactory.getLogger(ContactRestController.class);
	private ContactService contactService;
	
	private AppProperties appProps;
	
	public ContactRestController (ContactService contactService, AppProperties appProps) {
		this.contactService=contactService;
		this.appProps = appProps;
		System.out.println(this.contactService.getClass().getName());
	}
	
	@PostMapping("/saveContact")
	public ResponseEntity<String> saveContact(@RequestBody Contact contact){
		logger.debug("*** saveContact()- Execution started ");
		try {
			boolean isSaved =contactService.saveContact(contact);
			if(isSaved) {
				logger.info("saveContact() -- contact Saaved***");
				String succMsg = appProps.getMessages().get("savedContactSuccess");
				return new ResponseEntity<>(succMsg, HttpStatus.CREATED);
			}
 		}			catch(Exception e){
				logger.error("** Exception occured **"+ e.getMessage());
			}
			logger.info("saveContact () -- contact is not saaved");
			logger.debug("saveContact () execution ended ");
			String failMsg = appProps.getMessages().get("saveContactFaailed");
			return new ResponseEntity<>(failMsg, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	@GetMapping
	public ResponseEntity<List<Contact>> getAllContact(){
logger.debug("*** getAllContact()-- Excution started");
		List<Contact> allContacts = null;
		try {
				allContacts =	contactService.getAllContacts();
			if(allContacts.isEmpty()) {
				logger.info("GetAllContacts ---> Records not avialbale");
			}
		}
		catch (Exception e){
				logger.error("Exception occured"+ e.getMessage());
			}
		logger.debug("getAllContacts execution ended");
		return new ResponseEntity<>(allContacts,HttpStatus.OK);
		
		}

	@GetMapping("/{contactId}")
	public ResponseEntity<Contact> getContactById(@PathVariable Integer contactId){
		logger.debug("** getContactById() Execution Started **");
		Contact contact= null;
		try {
			contact = contactService.getContactById(contactId);
			if(contact== null) {
				logger.info(" getContactId() -- Record Not Available");
			}
		}
			catch (Exception e){
			logger.error("Exception occerd"+ e.getMessage());	
			}
		
	logger.debug("getContactById()--Exception ended");
	return new ResponseEntity<>(contact, HttpStatus.OK);
	}
	
@DeleteMapping("/{contactId}")
public ResponseEntity<String> deleteContactById(@PathVariable Integer contactId){
	logger.debug("** deleteContactById - Execution Started");
	ResponseEntity<String> responseEntity= null;
	try {
		
boolean isDeleted = contactService.deleteContactById(contactId);
if(isDeleted)
	return new ResponseEntity<>("Deleted", HttpStatus.OK);
String deleteMsg = appProps.getMessages().get("deleteContactSuccess");
return new  ResponseEntity<>(deleteMsg, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	catch(Exception e) {
	logger.error("** Exception occured ** "+ e.getMessage());
	}
	logger.debug("*DeleteContacet-ById Execution Failed*");
	String deleteMsg = appProps.getMessages().get("deleteContactFailed");
	responseEntity = new ResponseEntity<>(deleteMsg, HttpStatus.INTERNAL_SERVER_ERROR);
	return responseEntity;
}  
}

