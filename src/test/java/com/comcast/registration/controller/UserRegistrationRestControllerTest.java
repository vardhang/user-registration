package com.comcast.registration.controller;

import com.comcast.registration.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Vardhana Rao Gude on 7/13/2016.
 */

@Transactional
@DatabaseSetup(
		value = {
				"classpath:/Users.xml"
		}
)
public class UserRegistrationRestControllerTest extends AbstractWebApplicationContextTest
{

	@Inject
	private ObjectMapper objectMapper;

	@Test
	public void testInjection() {
		assertNotNull("MockMVC should be initialized.", mockMvc);
	}

	@Test
	public void testAllUsers() throws Exception{

		mockMvc.perform(get("/user/").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is("1")))
				.andExpect(jsonPath("$[0].userName", is("VARDHAN")))
				.andExpect(jsonPath("$[0].email", is("vardhang@gmail.com")))
				.andExpect(jsonPath("$[1].id", is("2")))
				.andExpect(jsonPath("$[1].userName", is("UDAY")))
				.andExpect(jsonPath("$[1].email", is("udaykumar1113@gmail.com")));
	}


	@Test
	public void testGetUser() throws Exception{

		mockMvc.perform(get("/user/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", is("1")))
				.andExpect(jsonPath("$.userName", is("VARDHAN")))
				.andExpect(jsonPath("$.email", is("vardhang@gmail.com")));
	}

	@Test
	public void testCreateUser() throws Exception{
		User user = new User();
		user.setUserName("JOHN");
		user.setEmail("john@gmail.com");

		mockMvc.perform(post("/user/").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user)))
				.andDo(print())
				.andExpect(status().isOk());
	}


	@Test
	public void testDeleteUser() throws Exception{

		mockMvc.perform(delete("/user/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().is2xxSuccessful());
	}
}
