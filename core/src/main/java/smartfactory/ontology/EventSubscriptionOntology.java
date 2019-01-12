package smartfactory.ontology;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import smartfactory.models.Event;

public class EventSubscriptionOntology extends Ontology {

	public static final String ONTOLOGY_NAME = "event-subscription-ontology";

	private EventSubscriptionOntology() {
		super(ONTOLOGY_NAME, BasicOntology.getInstance());

		try {
			Event.registerSchemaInOntology(this);
		} catch (OntologyException | NoSuchFieldException | SecurityException e) {
			logger.error("", e);
		}
	}

	private static Ontology theInstance = new EventSubscriptionOntology();

	public static Ontology getInstance() {
		return theInstance;
	}

	private static final long serialVersionUID = 1550065339069858588L;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
