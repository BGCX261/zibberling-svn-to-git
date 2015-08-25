package org.akqa.mmi.rdf.listeners;

import static org.junit.Assert.*;
import com.hp.hpl.jena.rdf.model.Model
import com.hp.hpl.jena.rdf.model.ModelFactory
import com.hp.hpl.jena.shared.PrefixMapping
import com.hp.hpl.jena.rdf.model.Resource
import com.hp.hpl.jena.rdf.model.Property
import com.hp.hpl.jena.rdf.model.Literal
import com.hp.hpl.jena.ontology.OntModel
import com.hp.hpl.jena.rdf.model.InfModel
import com.hp.hpl.jena.ontology.OntModelSpec
import org.akqa.mmi.rdf.builders.*
import org.mindswap.pellet.jena.PelletReasonerFactory
import org.akqa.mmi.InteractionManager
import org.akqa.mmi.DefaultTest

class MmiListenerTest extends DefaultTest {
	
	def model
	def builder
	def listener 
	
	def load = {filename, model ->
		def tmpModel = super.load(filename)
		model.withDefaultMappings(tmpModel.getNsPrefixMap())
		model.add tmpModel
	}
	
	void setUp() {
		super.setUp()
		def lm
		model = ModelFactory.createDefaultModel()
		load("scxml.ttl",model)
		load("mmi.ttl",model)
		load("kotg.ttl",model)
		load("kotw.ttl",model)
		listener = new MmiListener()
		model.register(new MmiListener())
		builder = new RdfBuilder(model.getNsPrefixMap())
		
	}
	
	void testQuery() {
		def event =  builder.Model {
			Resource("urn:test") {
				Predicate("rdf:type") {
					Resource("kotg:LifeExperienceStartEvent")
				}
			}
		}
		
		model.add(event)
		
		def statement
		model.listStatements(model.createResource("urn:test"), null, model.createResource("http://www.telekom.com/ns/kotg/LifeExperienceStartEvent")).each {
			statement = it
		}
		
		def results = listener.query(statement)
		//results.write(output, "TTL")
		// output.toString()
		
	}
		
}
