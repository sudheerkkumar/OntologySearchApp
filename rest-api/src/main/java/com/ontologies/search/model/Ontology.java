package com.ontologies.search.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ontology")
public class Ontology {

	@Id
	private String ontologyId;
	private String title;
	private String description;
	private List<String> definitionProperties;
	private List<String> synonymProperties;

	public Ontology() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ontology(String ontologyId, String title, String description, List<String> definitionProperties,
			List<String> synonymProperties) {
		super();
		this.ontologyId = ontologyId;
		this.title = title;
		this.description = description;
		this.definitionProperties = definitionProperties;
		this.synonymProperties = synonymProperties;
	}

	public String getOntologyId() {
		return ontologyId;
	}

	public void setOntologyId(String ontologyId) {
		this.ontologyId = ontologyId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getDefinitionProperties() {
		return definitionProperties;
	}

	public void setDefinitionProperties(List<String> definitionProperties) {
		this.definitionProperties = definitionProperties;
	}

	public List<String> getSynonymProperties() {
		return synonymProperties;
	}

	public void setSynonymProperties(List<String> synonymProperties) {
		this.synonymProperties = synonymProperties;
	}

	@Override
	public String toString() {
		return "Ontology [ontologyId=" + ontologyId + ", title=" + title + ", description=" + description
				+ ", definitionProperties=" + definitionProperties + ", synonymProperties=" + synonymProperties + "]";
	}

}
