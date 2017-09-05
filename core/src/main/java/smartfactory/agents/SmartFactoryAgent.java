package smartfactory.agents;

import jade.core.Agent;

public class SmartFactoryAgent extends Agent {

	@Override
	protected void setup() {
		initializeGUI();
	}

	@Override
	protected void takeDown() {
		finalizeGUI();
	}

	protected void initializeGUI() {
	}

	protected void finalizeGUI() {
	}

	private static final long serialVersionUID = -5660298497795218024L;
}
