package com.geoffdigital.ngrest.application.controller;

import static com.geoffdigital.ngrest.application.controller.fixture.RestDataFixture.standardStudentJSON;
import static com.geoffdigital.ngrest.application.controller.fixture.RestEventFixtures.studentCreated;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import com.geoffdigital.ngrest.application.services.StudentService;
import com.geoffdigital.ngrest.domain.events.students.CreateStudentEvent;

public class CreateNewStudentIntegrationTest {

	MockMvc mockMvc;

	@InjectMocks
	StudentCommandsController controller;

	@Mock
	StudentService studentService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		this.mockMvc = standaloneSetup(controller)
				.setMessageConverters(new MappingJackson2HttpMessageConverter()).build();

		when(studentService.createStudent(any(CreateStudentEvent.class))).thenReturn(
				studentCreated(UUID.fromString("f3512d26-72f6-4290-9265-63ad69eccc13")));
	}

	@Test
	public void thatCreateStudentUsesHttpCreated() throws Exception {
		this.mockMvc.perform(
				post("/students")
					.content(standardStudentJSON())
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().isCreated());
	}

	@Test
	public void thatCreateStudentRendersAsJson() throws Exception {
		this.mockMvc.perform(
				post("/students")
					.content(standardStudentJSON())
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
					//.andExpect(jsonPath("$.items['" + YUMMY_ITEM + "']").value(12))
					.andExpect(jsonPath("$.id").value("f3512d26-72f6-4290-9265-63ad69eccc13"));
	}

	@Test
	public void thatCreateStudentPassesLocationHeader() throws Exception {
		this.mockMvc.perform(
				post("/students")
					.content(standardStudentJSON())
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(header().string("Location", Matchers.endsWith("/students/f3512d26-72f6-4290-9265-63ad69eccc13")));
	}
	
}
