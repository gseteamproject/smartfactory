package smartfactory.ontology;

import jade.content.Predicate;
import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.PredicateSchema;
import jade.content.schema.PrimitiveSchema;

// TODO : think if is it possible to combine with ServiceRefused

public class ServiceFailed implements Predicate {

	private String serviceName;

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	private String failedReason;

	public void setFailedReason(String failedReason) {
		this.failedReason = failedReason;
	}

	public String getFailedReason() {
		return this.failedReason;
	}

	public static String getSchemaName() {
		return ServiceFailed.class.getSimpleName();
	}

	private static String getSchemaName(String field) throws NoSuchFieldException, SecurityException {
		return ServiceFailed.class.getDeclaredField(field).getName();
	}

	public static void registerSchemaInOntology(Ontology ontology)
			throws OntologyException, NoSuchFieldException, SecurityException {
		PredicateSchema ps = new PredicateSchema(getSchemaName());
		ps.add(getSchemaName("serviceName"), (PrimitiveSchema) ontology.getSchema(BasicOntology.STRING));
		ps.add(getSchemaName("failedReason"), (PrimitiveSchema) ontology.getSchema(BasicOntology.STRING));
		ontology.add(ps, ServiceFailed.class);
	}

	private static final long serialVersionUID = 4450090728522972269L;
}
