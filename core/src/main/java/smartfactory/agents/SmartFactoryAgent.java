package smartfactory.agents;

import jade.core.Agent;

public class SmartFactoryAgent extends Agent {

	@Override
	final protected void setup() {
		initializeData();
		initializeGUI();
		initializeBehaviours();
		registerLanguages();
		registerOntologies();
		registerServices();
	}

	@Override
	final protected void takeDown() {
		finalizeGUI();
	}

	protected void initializeGUI() {
	}

	protected void finalizeGUI() {
	}

	protected void initializeBehaviours() {
	}

	protected void initializeData() {
	}

	protected void registerServices() {
	}

	protected void deregisterServices() {
	}

	protected void registerLanguages() {
	}

	protected void registerOntologies() {
	}

	private static final long serialVersionUID = -5660298497795218024L;
}
