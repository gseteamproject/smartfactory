package smartfactory.serviceProvisioning.ontology;

import jade.content.Predicate;
import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.PredicateSchema;
import jade.content.schema.PrimitiveSchema;

public class ServiceRefusal implements Predicate {

	private String serviceName;

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	private String refusalReason;

	public void setRefusalReason(String refusalReason) {
		this.refusalReason = refusalReason;
	}

	public String getRefusalReason() {
		return this.refusalReason;
	}

	public static String getSchemaName() {
		return ServiceRefusal.class.getSimpleName();
	}

	private static String getSchemaName(String field) throws NoSuchFieldException, SecurityException {
		return ServiceRefusal.class.getDeclaredField(field).getName();
	}

	public static void registerSchemaInOntology(Ontology ontology)
			throws OntologyException, NoSuchFieldException, SecurityException {
		PredicateSchema ps = new PredicateSchema(getSchemaName());
		ps.add(getSchemaName("serviceName"), (PrimitiveSchema) ontology.getSchema(BasicOntology.STRING));
		ps.add(getSchemaName("refusalReason"), (PrimitiveSchema) ontology.getSchema(BasicOntology.STRING));
		ontology.add(ps, ServiceRefusal.class);
	}

	private static final long serialVersionUID = 2675661862292845596L;
}
