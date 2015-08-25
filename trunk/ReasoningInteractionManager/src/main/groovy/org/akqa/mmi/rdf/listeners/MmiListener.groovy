package org.akqa.mmi.rdf.listeners

import org.akqa.mmi.rdf.builders.RdfBuilder

import com.hp.hpl.jena.rdf.listeners.StatementListener
import com.hp.hpl.jena.rdf.model.Statement
import com.hp.hpl.jena.query.QueryExecutionFactory
import com.hp.hpl.jena.rdf.model.Model

class MmiListener extends StatementListener {

	public boolean check(Statement s)  {
		((s.getObject().isResource()) && (s.getObject().asResource().getLocalName().equals("TransitionTriggerEvent")))
	}
	
	def send = {
		println "Sending event"
	}
	
	def raise =  {
		println "Raising event"
	}
	
	public void execute(Model executionBlock) {
		executionBlock.listStatements().each {
			println it.getPredicate().getLocalName()
			//this."${it.getObject().asResource().getLocalName}".call()
		}
	}
	
	public Model query (Statement s) {
		def sparql = ""
		s.getModel().getNsPrefixMap().each() {
			sparql += "PREFIX ${it.key}: <${it.value}> \n"
		}
		
		sparql += """
			CONSTRUCT {
				?executionBlock ?command ?event.
				?event ?predicate ?object.
			}
			WHERE {
				{?transition scxml:event <${s.getObject().asResource().getURI()}>.
				 ?transition scxml:hasOnEntry ?executionBlock.
				 ?executionBlock ?command ?event.
				 ?event ?predicate ?object.}
				UNION
				{?transition scxml:event <${s.getObject().asResource().getURI()}>.
				 ?transition scxml:target ?state.
				 ?state scxml:hasOnEntry ?executionBlock.
				 ?executionBlock ?command ?event.
				 ?event ?predicate ?object.}
			}
		"""

		def queryExecution = QueryExecutionFactory.create(sparql, s.getModel())
		execute queryExecution.execConstruct()
	}
	
	def remove = {
		
	}
	
	public void addedStatement(Statement s) {
		println "statement added"
		/*if (check(s)) {
			execute s
			remove s
		}*/
	}
	
}
