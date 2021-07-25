package com.ontologies.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.http.client.methods.RequestBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ontologies.search.controller.OntologyController;
import com.ontologies.search.dao.OntologyDAO;
import com.ontologies.search.model.Ontology;
import com.ontologies.search.service.OntologyService;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = OntologyController.class)
public class OntologyControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private OntologyDAO mockDao;

	String exampleJson = "{\"ontologyId\":\"mockMondo\",\"title\":\"mock Disease Ontology\", \"description\":\"A test for ontology creation\"}";

	@Test
	public void testGetController() throws Exception {
		List<String> defs = new ArrayList<String>();
		defs.add("http://purl.org/dc/elements/1.1/description");
		List<String> syms = new ArrayList<String>();
		Ontology ontology = new Ontology("mondo", "Mondo Disease Ontology",
				"A semi-automatically constructed ontology that merges in multiple disease resources to yield a coherent merged ontology.",
				defs, syms);
		Optional<Ontology> mockOntology = Optional.of(ontology);
		Mockito.when(mockDao.findByontologyId(Mockito.anyString())).thenReturn(mockOntology);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/getOntology/mondo")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{ontologyId: mondo,title: Mondo Disease Ontology}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void createOntology() throws Exception {

		Ontology ontology = new Ontology("mockMondo", "mock Disease Ontology", "A test for ontology creation",
				new ArrayList<String>(), new ArrayList<String>());

		Mockito.when(mockDao.save(Mockito.any(Ontology.class))).thenReturn(ontology);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/create")
				.accept(MediaType.APPLICATION_JSON).content(exampleJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}
}
