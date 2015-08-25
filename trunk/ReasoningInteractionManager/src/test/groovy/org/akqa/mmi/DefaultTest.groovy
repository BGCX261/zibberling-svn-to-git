package org.akqa.mmi;

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
import com.hp.hpl.jena.reasoner.ReasonerRegistry

class DefaultTest extends GroovyTestCase {

	static RESOURCE_PATH = InteractionManager.RESOURCE_PATH
	
	def builder
	def output
	
	void setUp() {
		output = new ByteArrayOutputStream()
	}
	
	public Model load (String fileName) {
		return ModelFactory.createDefaultModel().read(new FileInputStream(RESOURCE_PATH + fileName), null, "TTL")
	}
	
	public Model load (String fileName, Model m) {
		def tmpModel = this.load(fileName)
		m.setNsPrefixes(tmpModel.getNsPrefixMap())
		return m.add(tmpModel)
	}
	
	def reason = {it ->
		InfModel inferredModel = ModelFactory.createInfModel(PelletReasonerFactory.theInstance().create(), it)
		//InfModel inferredModel = ModelFactory.createInfModel(ReasonerRegistry.getOWLReasoner(), it)
	}
	
	void testInit() {
		assert true
	}
}
