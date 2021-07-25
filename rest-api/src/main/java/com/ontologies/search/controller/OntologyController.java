package com.ontologies.search.controller;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.Optional;

import org.apache.http.client.ClientProtocolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ontologies.search.dao.OntologyDAO;
import com.ontologies.search.model.Ontology;
import com.ontologies.search.service.OntologyService;

@RestController
@RequestMapping("/api")
public class OntologyController {

	@Autowired
	public OntologyDAO dao;

	@Autowired
	public OntologyService service;

	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ontology> create(@RequestBody Ontology onto) throws ServerException {

		Ontology newOnto = dao.save(onto);
		if (newOnto == null)
			throw new ServerException("Bill creation failed");
		else
			return new ResponseEntity<Ontology>(newOnto, HttpStatus.CREATED);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/getOntology/{id}")
	public ResponseEntity<Ontology> getOntologyById(@PathVariable(value = "id") String ontologyId)
			throws ClientProtocolException, IOException, ParseException {
		Optional<Ontology> ontology = dao.findByontologyId(ontologyId);
		System.out.println(ontology.toString());
		Ontology newOnto = null;
		if (!ontology.isPresent()) {
			// get ontology from ols service
			ontology = service.getOntologyFromOLS(ontologyId);
			if (!ontology.isPresent())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ontology not found");
			newOnto = dao.save(ontology.get());
		}

		return new ResponseEntity<Ontology>(ontology.isPresent() ? ontology.get() : newOnto, HttpStatus.OK);
	}
}
