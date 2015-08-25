package org.akqa.mmi.ontology

import static org.junit.Assert.*

import com.hp.hpl.jena.rdf.model.Model
import com.hp.hpl.jena.rdf.model.ModelFactory
import com.hp.hpl.jena.shared.PrefixMapping
import com.hp.hpl.jena.rdf.model.Resource
import com.hp.hpl.jena.rdf.model.Property
import com.hp.hpl.jena.rdf.model.Literal
import org.akqa.mmi.rdf.builders.*

class MmiTest extends DefaultOntologyTest {
	
	void setUp() {
		filename = "mmi.ttl"
		init()
	}
	
	void testDataProperty() {
		ontModelBuilder.Model {
			Resource("urn:test-event") {
				Predicate("mmi:data") {
					Resource("urn:test-data")
				}
			}
		}
		def materialisations = builder.Model {
			Resource("urn:test-data") {
				Predicate("provenance:qualifiedGeneration") {
					Resource("urn:test-event")
				}
			}
			Resource("urn:test-event") {
				Predicate("rdf:type") {
					Resource("mmi:Event")
				}
			}
		}
		assert reason(ontModel).containsAll(materialisations)
	}
	
}
