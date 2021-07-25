package com.gigabytes.testcontroller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gigabytes.controller.ContactRestController;
import com.gigabytes.entity.Contact;
import com.gigabytes.service.ContactService;

@WebMvcTest(value=ContactRestController.class)
public class ContactTestController {
	
	  @MockBean 
	  private ContactService service;
	  
	  @Autowired
	  private MockMvc mockMvc; 
	     
	  @Test
	  private void test_saveContact() throws Exception   {
	  when(service.saveContact(Mockito.any())).thenReturn(true);
	  Contact c = new Contact(101,"ankush","ankush.in","7525281");
	  ObjectMapper mapper= new ObjectMapper();
	  String json = null;
	  try {
		  json=mapper.writeValueAsString(c);
	  }
	  catch(JsonProcessingException e) {
		  e.printStackTrace();
	  }
	  MockHttpServletRequestBuilder reqBuilder=MockMvcRequestBuilders.post("api/contact")
			  .contentType("application/json")
	  			.content(json); 
	  	MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
	  	MockHttpServletResponse response =mvcResult.getResponse();
	  	int status = response.getStatus();
	  	assertEquals(201,status);
}
	  @Test
	  	private void test_saveContact_2() {
	  		
	  	}
}
