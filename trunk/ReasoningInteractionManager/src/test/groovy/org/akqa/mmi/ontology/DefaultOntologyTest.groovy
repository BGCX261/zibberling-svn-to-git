package org.akqa.mmi.ontology

import static org.junit.Assert.*

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

abstract class DefaultOntologyTest extends DefaultTest {

	def filename = ""
	
	def ontModel
	def ontModelBuilder
	
	void init() {
		output = new ByteArrayOutputStream()
		ontModel = ModelFactory.createDefaultModel()
		if(filename.length()) {
			ontModel.read(new FileInputStream(DefaultTest.RESOURCE_PATH + filename), null, "TTL")	
		}
		ontModelBuilder = new RdfBuilder(ontModel)
		
		builder = new RdfBuilder(ontModel.getNsPrefixMap())
	}
	
	
	void testParseOntology () {
		reason ontModel
	}
	
}
