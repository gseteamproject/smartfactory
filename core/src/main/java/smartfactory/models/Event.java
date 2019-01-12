package smartfactory.models;

import jade.content.Predicate;
import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.PredicateSchema;
import jade.content.schema.PrimitiveSchema;

public class Event implements Predicate {

	public final static String OPERATION_COMPLETED_SUCCESS = "operation-completed-success";

	public final static String OPERATION_COMPLETED_FAILURE = "operation-completed-failure";

	public final static String PROCESS_COMPLETED_SUCCESS = "process-completed-success";

	public final static String PROCESS_COMPLETED_FAILURE = "process-completed-failure";

	private String id;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static String getSchemaName() {
		return Event.class.getSimpleName();
	}

	private static String getSchemaName(String field) throws NoSuchFieldException, SecurityException {
		return Event.class.getDeclaredField(field).getName();
	}

	public static void registerSchemaInOntology(Ontology ontology)
			throws OntologyException, NoSuchFieldException, SecurityException {
		PredicateSchema ps = new PredicateSchema(getSchemaName());
		ps.add(getSchemaName("id"), (PrimitiveSchema) ontology.getSchema(BasicOntology.STRING));
		ontology.add(ps, Event.class);
	}

	private static final long serialVersionUID = -8592886826154107789L;
}
