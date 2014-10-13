package com.geoffdigital.ngrest.application.controller;

import static com.geoffdigital.ngrest.application.controller.fixture.RestEventFixtures.studentDetailsEvent;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import com.geoffdigital.ngrest.application.services.StudentService;
import com.geoffdigital.ngrest.domain.events.students.RequestStudentDetailsEvent;


public class ViewStudentXmlIntegrationTest {

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
				.setMessageConverters(new MappingJackson2HttpMessageConverter(),
						new Jaxb2RootElementHttpMessageConverter()).build();
	}

	@Test
	public void thatViewStudentRendersXMLCorrectly() throws Exception {
		when(studentService.requestStudentDetails(any(RequestStudentDetailsEvent.class))).thenReturn(
				studentDetailsEvent(id));

		this.mockMvc.perform(
				get("/students/{id}", id.toString())
					.accept(MediaType.TEXT_XML))
					.andDo(print())
					.andExpect(content().contentType(MediaType.TEXT_XML))
					.andExpect(xpath("/student/id").string(id.toString()));
	}

	@Test
	public void thatViewStudentRendersJsonCorrectly() throws Exception {
		when(studentService.requestStudentDetails(any(RequestStudentDetailsEvent.class))).thenReturn(
				studentDetailsEvent(id));

		this.mockMvc.perform(
				get("/students/{id}", id.toString())
					.accept(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.id").value(id.toString()));
	}
	
}
