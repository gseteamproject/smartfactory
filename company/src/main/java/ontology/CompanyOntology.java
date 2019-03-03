package ontology;

import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;

public class CompanyOntology extends Ontology {

	private static final long serialVersionUID = -2804509227269551600L;

	public static final String ONTOLOGY_NAME = "company-ontology";

	private CompanyOntology() {
		super(ONTOLOGY_NAME, BasicOntology.getInstance());
	}

	private static Ontology theInstance = new CompanyOntology();

	public static Ontology getInstance() {
		return theInstance;
	}
}
