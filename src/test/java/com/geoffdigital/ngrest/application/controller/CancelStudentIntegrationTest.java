package com.geoffdigital.ngrest.application.controller;

import static com.geoffdigital.ngrest.application.controller.fixture.RestEventFixtures.studentDeleted;
import static com.geoffdigital.ngrest.application.controller.fixture.RestEventFixtures.studentDeletedFailed;
import static com.geoffdigital.ngrest.application.controller.fixture.RestEventFixtures.studentDeletedNotFound;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
import com.geoffdigital.ngrest.domain.events.students.DeleteStudentEvent;

public class CancelStudentIntegrationTest {

	MockMvc mockMvc;

	@InjectMocks
	StudentCommandsController controller;

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
	public void thatDeleteStudentUsesHttpOkOnSuccess() throws Exception {

		when(studentService.deleteStudent(any(DeleteStudentEvent.class)))
			.thenReturn(
					studentDeleted(id));

		this.mockMvc.perform(
				delete("/students/{id}", id.toString())
					.accept(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().isOk());

		verify(studentService).deleteStudent(argThat(
				Matchers.<DeleteStudentEvent>hasProperty("id",
						eq(id))));
	}

	@Test
	public void thatDeleteStudentUsesHttpNotFoundOnEntityLookupFailure() throws Exception {

		when(studentService.deleteStudent(any(DeleteStudentEvent.class)))
			.thenReturn(
					studentDeletedNotFound(id));

		this.mockMvc.perform(
				delete("/students/{id}", id.toString())
					.accept(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().isNotFound());
	}

	@Test
	public void thatDeleteStudentUsesHttpForbiddenOnEntityDeletionFailure() throws Exception {

		when(studentService.deleteStudent(any(DeleteStudentEvent.class)))
			.thenReturn(
					studentDeletedFailed(id));

		this.mockMvc.perform(
				delete("/students/{id}", id.toString())
					.accept(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().isForbidden());
	}

}
