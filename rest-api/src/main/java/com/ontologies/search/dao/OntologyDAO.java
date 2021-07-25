package com.ontologies.search.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ontologies.search.model.Ontology;

@Repository
public interface OntologyDAO extends MongoRepository<Ontology, Long> {

	Optional<Ontology> findByontologyId(String ontologyId);
}
