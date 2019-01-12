package smartfactory.ontology;

import jade.content.Predicate;
import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.PredicateSchema;
import jade.content.schema.PrimitiveSchema;

// TODO : think if it possible to combine with ServiceProposal

public class ServiceCompleted implements Predicate {

	private String serviceName;

	public String getServiceName() {
		return this.serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	private int durationCompleted;

	public int getDurationCompleted() {
		return this.durationCompleted;
	}

	public void setDurationCompleted(int durationCompleted) {
		this.durationCompleted = durationCompleted;
	}

	public static String getSchemaName() {
		return ServiceCompleted.class.getSimpleName();
	}

	private static String getSchemaName(String field) throws NoSuchFieldException, SecurityException {
		return ServiceCompleted.class.getDeclaredField(field).getName();
	}

	public static void registerSchemaInOntology(Ontology ontology)
			throws OntologyException, NoSuchFieldException, SecurityException {
		PredicateSchema ps = new PredicateSchema(getSchemaName());
		ps.add(getSchemaName("serviceName"), (PrimitiveSchema) ontology.getSchema(BasicOntology.STRING));
		ps.add(getSchemaName("durationCompleted"), (PrimitiveSchema) ontology.getSchema(BasicOntology.INTEGER));
		ontology.add(ps, ServiceCompleted.class);
	}

	private static final long serialVersionUID = -4701918345292911787L;
}
