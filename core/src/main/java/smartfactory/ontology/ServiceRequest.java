package smartfactory.ontology;

import jade.content.AgentAction;
import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.AgentActionSchema;
import jade.content.schema.PrimitiveSchema;

public class ServiceRequest implements AgentAction {

	private String serviceName;

	public String getServiceName() {
		return this.serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public static String getSchemaName() {
		return ServiceRequest.class.getSimpleName();
	}

	private static String getSchemaName(String field) throws NoSuchFieldException, SecurityException {
		return ServiceRequest.class.getDeclaredField(field).getName();
	}

	public static void registerSchemaInOntology(Ontology ontology)
			throws OntologyException, NoSuchFieldException, SecurityException {
		AgentActionSchema as = new AgentActionSchema(getSchemaName());
		as.add(getSchemaName("serviceName"), (PrimitiveSchema) ontology.getSchema(BasicOntology.STRING));
		ontology.add(as, ServiceRequest.class);
	}

	private static final long serialVersionUID = -6615935044238507896L;
}
