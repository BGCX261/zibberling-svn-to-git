package org.akqa.mmi.ontology

import static org.junit.Assert.*

import com.hp.hpl.jena.rdf.model.Model
import com.hp.hpl.jena.rdf.model.ModelFactory
import com.hp.hpl.jena.shared.PrefixMapping
import com.hp.hpl.jena.rdf.model.Resource
import com.hp.hpl.jena.rdf.model.Property
import com.hp.hpl.jena.rdf.model.Literal
import org.akqa.mmi.rdf.builders.*

class KotgTest extends DefaultOntologyTest {
	void setUp() {
		filename = "kotg.ttl"
		init()
		ontModel.add load("mmi.ttl")
		ontModel.add load("kotw.ttl")
		ontModel.add load("scxml.ttl")
		
	}
	
	void testTouchScreensOver1024By768AreHiResTouchScreenComponents() {
		ontModelBuilder.Model {
			Resource("urn:hiResComponent") {
				Predicate("rdf:type") {
					Resource("kotg:TouchScreenComponent")
				}
				Predicate("kotg:screenWidth") {
					Literal(1024)
				}
				Predicate("kotg:screenHeight") {
					Literal(768)
				}
			}
		}
		def materialisations = this.builder.Model {
			Resource("urn:hiResComponent") {
				Predicate("rdf:type") {
					Resource("kotg:HiResTouchScreenComponent")
				}
			}
		}
		assert reason(ontModel).containsAll(materialisations)
	}
	
	void testRoomsWithHealthCareProfessionalsAreRoomsWithMoreThan3() {
		ontModelBuilder.Model {
			Resource("urn:Jeff") {
				Predicate ("rdf:type") {
					Resource("kotw:HealthCareProfessional")
				}
				Predicate("kotg:occupies") {
					Resource("kotg:life-room")
				}
				Predicate("owl:differentFrom") {
					Resource("urn:Mike")
				}
				Predicate("owl:differentFrom") {
					Resource("urn:Jack")
				}
			}
			Resource("urn:Mike") {
				Predicate ("rdf:type") {
					Resource("kotw:HealthCareProfessional")
				}
				Predicate ("kotg:occupies") {
					Resource("kotg:life-room")
				}
				Predicate("owl:differentFrom") {
					Resource("urn:Jeff")
				}
				Predicate("owl:differentFrom") {
					Resource("urn:Jack")
				}
			}
			Resource("urn:Jack") {
				Predicate ("rdf:type") {
					Resource("kotw:HealthCareProfessional")
				}
				Predicate ("kotg:occupies") {
					Resource("kotg:life-room")
				}
				Predicate("owl:differentFrom") {
					Resource("urn:Mike")
				}
				Predicate("owl:differentFrom") {
					Resource("urn:Jeff")
				}
			}
		}
		def materialisations = builder.Model {
			Resource("kotg:life-room") {
				Predicate ("rdf:type") {
						Resource("kotg:RoomsWithHealthCareProfessionals")
				}
			}
		}
		assert reason(ontModel).containsAll(materialisations)
	}
	
	void testLifeExperienceStartEvent() {
		ontModelBuilder.Model {
			Resource("urn:Mike") {
				Predicate ("rdf:type") {
					Resource("kotw:HealthCareProfessional")
				}
				Predicate ("kotg:occupies") {
					Resource("kotg:life-room")
				}
				Predicate("owl:differentFrom") {
					Resource("urn:Jeff")
				}
				Predicate("owl:differentFrom") {
					Resource("urn:Jack")
				}
			}
			Resource("urn:Jack") {
				Predicate ("rdf:type") {
					Resource("kotw:HealthCareProfessional")
				}
				Predicate ("kotg:occupies") {
					Resource("kotg:life-room")
				}
				Predicate("owl:differentFrom") {
					Resource("urn:Mike")
				}
				Predicate("owl:differentFrom") {
					Resource("urn:Jeff")
				}
			}
			Resource("urn:Jeff") {
				Predicate ("rdf:type") {
					Resource("kotw:HealthCareProfessional")
				}
				Predicate("kotg:occupies") {
					Resource("kotg:life-room")
				}
				Predicate("owl:differentFrom") {
					Resource("urn:Mike")
				}
				Predicate("owl:differentFrom") {
					Resource("urn:Jack")
				}
			}
			Resource("urn:test-event") {
				Predicate("mmi:data") {
					Resource("urn:Jeff") 
				}
			}	
		}
		def materialisations = builder.Model {
			Resource("urn:test-event") {
				Predicate("rdf:type") {
					Resource("kotg:LifeExperienceStartEvent")
				}
			}
		}
		
		assert reason(ontModel).containsAll(materialisations)
	}
	
}
