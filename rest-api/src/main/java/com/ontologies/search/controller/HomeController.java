package com.ontologies.search.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public String hello() {
		return "<h1>Welcome to Ontology Search Tool</h1>" + "<h2>Use api/getOntology/{id} to get Ontology</h2>"
				+ "<h2>Use api/create to create Ontology</h2>"
				+ "<p>Refer Swagger Documention: <a href=\"http://localhost:8888/swagger-ui.html\">http://localhost:8888/swagger-ui.html</a>";
	}
}
