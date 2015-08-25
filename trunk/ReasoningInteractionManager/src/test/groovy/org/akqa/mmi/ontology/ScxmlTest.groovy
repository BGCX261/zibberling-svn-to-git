package org.akqa.mmi.ontology;

import static org.junit.Assert.*;

import com.hp.hpl.jena.rdf.model.Model
import com.hp.hpl.jena.rdf.model.ModelFactory
import com.hp.hpl.jena.shared.PrefixMapping
import com.hp.hpl.jena.rdf.model.Resource
import com.hp.hpl.jena.rdf.model.Property
import com.hp.hpl.jena.rdf.model.Literal
import org.akqa.mmi.rdf.builders.*

class ScxmlTest extends DefaultOntologyTest{
	
	
	void setUp() {
		filename = "scxml.ttl"
		init()
	}
	
	void testSubClasses() {
		ontModelBuilder.Model {
			Resource("urn:test") {
				Predicate("rdf:type") {
					Resource("scxml:CurrentState")
				}
			}
		}
		def materialisations = this.builder.Model {
			Resource("urn:test") {
				Predicate("rdf:type") {
					Resource("scxml:State")
				}
			}
		}
		assert reason(ontModel).containsAll(materialisations)
	}
	
}
