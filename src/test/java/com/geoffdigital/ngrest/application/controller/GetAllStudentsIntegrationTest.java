package com.geoffdigital.ngrest.application.controller;

import static com.geoffdigital.ngrest.application.controller.fixture.RestDataFixture.allStudents;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.geoffdigital.ngrest.application.controller.fixture.RestDataFixture;
import com.geoffdigital.ngrest.application.services.StudentService;
import com.geoffdigital.ngrest.domain.events.students.RequestAllStudentsEvent;


public class GetAllStudentsIntegrationTest {

	MockMvc mockMvc;

	@InjectMocks
	StudentQueriesController controller;

	@Mock
	StudentService studentService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		this.mockMvc = standaloneSetup(controller).build();

		when(studentService.requestAllStudents(any(RequestAllStudentsEvent.class))).thenReturn(allStudents());
	}

	@Test
	public void thatGetStudentsRendersAsJson() throws Exception {
		this.mockMvc.perform(
				get("/students")
					.accept(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$[0].items['" + RestDataFixture.FIRST_NAME + "']").value(12));
	}
	
}
