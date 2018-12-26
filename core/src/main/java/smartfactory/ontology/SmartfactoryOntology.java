package smartfactory.ontology;

import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;

public class SmartfactoryOntology extends Ontology {

	public static String ONTOLOGY_NAME = "smartfactory-ontology";

	private SmartfactoryOntology() {
		super(ONTOLOGY_NAME, BasicOntology.getInstance());
	}

	private static Ontology theInstance = new SmartfactoryOntology();

	public static Ontology getInstance() {
		return theInstance;
	}

	private static final long serialVersionUID = -3101554763297348037L;
}
