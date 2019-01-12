package smartfactory.ontology;

import jade.content.Predicate;
import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.PredicateSchema;
import jade.content.schema.PrimitiveSchema;

// TODO : rename to ServiceAgree or smth

public class ServiceProposal implements Predicate {

	private String serviceName;

	public String getServiceName() {
		return this.serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	private int durationEstimated;

	public int getDurationEstimated() {
		return this.durationEstimated;
	}

	public void setDurationEstimated(int durationEstimated) {
		this.durationEstimated = durationEstimated;
	}

	public static String getSchemaName() {
		return ServiceProposal.class.getSimpleName();
	}

	private static String getSchemaName(String field) throws NoSuchFieldException, SecurityException {
		return ServiceProposal.class.getDeclaredField(field).getName();
	}

	public static void registerSchemaInOntology(Ontology ontology)
			throws OntologyException, NoSuchFieldException, SecurityException {
		PredicateSchema ps = new PredicateSchema(getSchemaName());
		ps.add(getSchemaName("serviceName"), (PrimitiveSchema) ontology.getSchema(BasicOntology.STRING));
		ps.add(getSchemaName("durationEstimated"), (PrimitiveSchema) ontology.getSchema(BasicOntology.INTEGER));
		ontology.add(ps, ServiceProposal.class);
	}

	private static final long serialVersionUID = 6003301728852555530L;
}
