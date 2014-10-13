package com.geoffdigital.ngrest.application.controller;

import static com.geoffdigital.ngrest.application.controller.fixture.RestEventFixtures.studentDetailsEvent;
import static com.geoffdigital.ngrest.application.controller.fixture.RestEventFixtures.studentDetailsNotFound;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import com.geoffdigital.ngrest.application.services.StudentService;
import com.geoffdigital.ngrest.domain.events.students.RequestStudentDetailsEvent;

public class ViewStudentIntegrationTest {

	MockMvc mockMvc;

	@InjectMocks
	StudentQueriesController controller;

	@Mock
	StudentService studentService;

	UUID id = UUID.fromString("f3512d26-72f6-4290-9265-63ad69eccc13");

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		this.mockMvc = standaloneSetup(controller)
				.setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
	}

	@Test
	public void thatViewStudentUsesHttpNotFound() throws Exception {
		when(studentService.requestStudentDetails(any(RequestStudentDetailsEvent.class))).thenReturn(
				studentDetailsNotFound(id));

		this.mockMvc.perform(
				get("/students/{id}", id.toString())
					.accept(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().isNotFound());
	}

	@Test
	public void thatViewStudentUsesHttpOK() throws Exception {
		when(studentService.requestStudentDetails(any(RequestStudentDetailsEvent.class))).thenReturn(
				studentDetailsEvent(id));

		this.mockMvc.perform(
				get("/students/{id}", id.toString())
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
	}

	@Test
	public void thatViewStudentRendersCorrectly() throws Exception {
		when(studentService.requestStudentDetails(any(RequestStudentDetailsEvent.class))).thenReturn(
				studentDetailsEvent(id));

		this.mockMvc.perform(
				get("/students/{id}", id.toString())
					.accept(MediaType.APPLICATION_JSON))
					//.andExpect(jsonPath("$.items['" + YUMMY_ITEM + "']").value(12))
					.andExpect(jsonPath("$.id").value(id.toString()));
	}
	
}
