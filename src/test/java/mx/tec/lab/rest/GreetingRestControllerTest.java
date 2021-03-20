package mx.tec.lab.rest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
class GreetingRestControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void givenAGreetingRequest_WhenEmptyName_thenHelloWorld() throws Exception {
		this.mockMvc.perform(get("/greeting"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("content", equalTo("Hello, World!")));
	}
	
	@Test
	public void givenAGreetingRequest_WhenProvidedName_thenHelloName() throws Exception {
		this.mockMvc.perform(get("/greeting?name=Mario Marquez"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("content", equalTo("Hello, Mario Marquez!")));
	}
	
	@Test
	public void givenAGreetingRequest_WhenNullName_thenHelloWorld() throws Exception {
		this.mockMvc.perform(get("/greeting?name="))
			.andExpect(status().isOk())
			.andExpect(jsonPath("content", equalTo("Hello, World!")));
	}
	
	@Test
	public void givenAGreetingRequest_WhenWrongURL_thenError404() throws Exception {
		this.mockMvc.perform(get("/greting"))
			.andExpect(status().isNotFound());
			//.andExpect(status().is(404));
	}
	
	@Test
	public void givenAGoodbyeRequest_WhenProvidedName_thenHelloName() throws Exception {
		this.mockMvc.perform(get("/goodbye?name=Mario Marquez"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("content", equalTo("Goodbye, Mario Marquez!")));
	}
	
	@Test
	public void givenAGoodbyeRequest_WhenNullName_thenHelloWorld() throws Exception {
		this.mockMvc.perform(get("/goodbye?name="))
			.andExpect(status().isOk())
			.andExpect(jsonPath("content", equalTo("Goodbye, World!")));
	}

	
}
