package smartfactory.serviceProvisioning.ontology;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;

public class ServiceProvisioningOntology extends Ontology {

	public static final String ONTOLOGY_NAME = "service-provisioning-ontology";

	private ServiceProvisioningOntology() {
		super(ONTOLOGY_NAME, BasicOntology.getInstance());

		try {
			ServiceRequest.registerSchemaInOntology(this);
			ServiceProposal.registerSchemaInOntology(this);
			ServiceRefusal.registerSchemaInOntology(this);
			ServiceCompleted.registerSchemaInOntology(this);
			ServiceFailed.registerSchemaInOntology(this);
		} catch (OntologyException | NoSuchFieldException | SecurityException e) {
			logger.error("", e);
		}
	}

	private static Ontology theInstance = new ServiceProvisioningOntology();

	public static Ontology getInstance() {
		return theInstance;
	}

	private static final long serialVersionUID = -3101554763297348037L;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
