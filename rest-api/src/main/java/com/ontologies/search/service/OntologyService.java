package com.ontologies.search.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.ontologies.search.model.Ontology;

@Service
public class OntologyService {

	public Optional<Ontology> getOntologyFromOLS(String ontologyId)
			throws ClientProtocolException, IOException, ParseException {
		// create a get call to the Ontology Lookup Service to get the Id information
		String uri = "https://www.ebi.ac.uk/ols/api/ontologies/" + ontologyId;
		Optional<Ontology> ontology = Optional.empty();

		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uri);

		CloseableHttpResponse response = client.execute(httpGet);

		if (response.getStatusLine().getStatusCode() == 200) {
			ontology = getOntologyObject(ontologyId, response.getEntity());

		}
		client.close();

		return ontology;
	}

	public Optional<Ontology> getOntologyObject(String ontologyId, HttpEntity responseEntity)
			throws ParseException, IOException {
		JSONObject jso = new JSONObject();
		JSONParser parser = new JSONParser();
		String content = EntityUtils.toString(responseEntity);
		jso = (JSONObject) parser.parse(content);

		JSONObject config = (JSONObject) jso.get("config");

		String title = (String) config.get("title");
		String description = (String) config.get("description");
		List<String> definitionProperties = (List<String>) config.get("definitionProperties");
		List<String> synonymProperties = (List<String>) config.get("synonymProperties");

		Ontology ontology = new Ontology(ontologyId, title, description, definitionProperties, synonymProperties);
		Optional<Ontology> optionalOntology = Optional.of(ontology);
		return optionalOntology;
	}

}
