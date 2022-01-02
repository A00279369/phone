package com.cisco.phoneapp.restapp.controllers;

import com.cisco.phoneapp.restapp.fixtures.UserFixture;
import com.cisco.phoneapp.restapp.repositories.PhoneRepository;
import com.cisco.phoneapp.restapp.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)   //Reset the database before each test
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class UserControllerSpringBootTest {

	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PhoneRepository phoneRepository;


	private UUID userId = UUID.fromString ("93f3ed0a-92bd-4c82-ba0e-c098111cef59");
	private UUID phoneId = UUID.fromString ("2a810635-18d0-40ce-ada6-0c0fd1181225");


	@Test
	@WithMockUser(username = "user" ,password = "password")
	public void getUserTest() throws Exception {
		assertThat(userRepository).isNotNull();
		this.mockMvc.perform(get("/user"))
		  .andDo(print()).andExpect(status().isOk())
		;
	}

	@Test
	@WithMockUser(username = "user" ,password = "password")
	public void deleteUserTest() throws Exception {
		assertThat(userRepository).isNotNull();
		this.mockMvc.perform(delete("/user/"+userId))
				.andDo(print()).andExpect(status().isOk());
	}


	@Test
	@WithMockUser(username = "user" ,password = "password")
	public void addUserTest() throws Exception {
		assertThat(userRepository).isNotNull();
		this.mockMvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(getJSON(UserFixture.getUser1()))
		)
				.andDo(print()).andExpect(status().isCreated());
	}



	@Test
	@WithMockUser(username = "user" ,password = "password")
	public void addPhoneToUser() throws Exception {
		assertThat(userRepository).isNotNull();
		this.mockMvc.perform(post("/user/"+userId.toString()+"/phone")
				.contentType(MediaType.APPLICATION_JSON)
				.content(getJSON(UserFixture.getPhone1(userId)))
		)
				.andDo(print()).andExpect(status().isCreated());
	}

	@Test
	@WithMockUser(username = "user" ,password = "password")
	public void deletePhoneTest() throws Exception {
		assertThat(userRepository).isNotNull();
		this.mockMvc.perform(delete("/user/phone/"+phoneId.toString()))
				    .andDo(print()).andExpect(status().isOk());
	}


	@Test
	@WithMockUser(username = "user" ,password = "password")
	public void listPhoneTest() throws Exception {
		assertThat(userRepository).isNotNull();
		this.mockMvc.perform(get("/user/"+userId.toString()+"/phone"))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "user" ,password = "password")
	public void updatePreferredPhoneNumberTest() throws Exception {
		this.mockMvc.perform(put("/user/"+userId.toString()+"?preferredPhoneNumber=+353866011765"   ))
				.andDo(print()).andExpect(status().isOk());

	}


    private String getJSON(Object object) throws JsonProcessingException {
		ObjectMapper mapper =
		new ObjectMapper().configure(MapperFeature.USE_ANNOTATIONS, false);
		return mapper.writeValueAsString(object);
	}

}