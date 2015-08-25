package org.akqa.mmi.ontology

import static org.junit.Assert.*

import com.hp.hpl.jena.rdf.model.Model
import com.hp.hpl.jena.rdf.model.ModelFactory
import com.hp.hpl.jena.shared.PrefixMapping
import com.hp.hpl.jena.rdf.model.Resource
import com.hp.hpl.jena.rdf.model.Property
import com.hp.hpl.jena.rdf.model.Literal
import org.akqa.mmi.rdf.builders.*

class KotwTest extends DefaultOntologyTest {
	
	void setUp() {
		filename = "kotw.ttl"
		init()
	}
	
	void testHealthCareProfessionals () {
		ontModelBuilder.Model {
			Resource("urn:medicalPerson") {
				Predicate("rdf:type") {
					Resource("kotw:Person")
				}
				Predicate("kotw:worksIn") {
					Resource("urn:hospital")
				}
			}
			Resource("urn:hospital") {
				Predicate("rdf:type") {
					Resource("kotw:Hospital")
				}
			}
		}
		def materialisations = this.builder.Model {
			Resource("urn:medicalPerson") {
				Predicate("rdf:type") {
					Resource("kotw:HealthCareProfessional")
				}
			}
		}
		assert reason(ontModel).containsAll(materialisations)
	} 
}
