package org.akqa.mmi

import com.hp.hpl.jena.rdf.model.Model
import com.hp.hpl.jena.rdf.model.ModelFactory
import com.hp.hpl.jena.shared.PrefixMapping

class InteractionManager {
	
	public static final RESOURCE_PATH = "resources/"
	
	def context = ModelFactory.createDefaultModel()
	
	def load = {
		ModelFactory.createDefaultModel().read(new FileInputStream(RESOURCE_PATH + it), null, "TTL")
	}
	
	
	static main(args) {
		def im = new InteractionManager()
		im.context.add(load("mmi.ttl"))
		im.context.add(load("scxml.ttl"))
		im.context.add(load("kotg.ttl"))
		im.context.add(load("kotw.ttl"))
		
	}
	

}
