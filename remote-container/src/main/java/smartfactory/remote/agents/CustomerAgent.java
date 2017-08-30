package smartfactory.remote.agents;

import jade.core.Agent;

public class CustomerAgent extends Agent {

	private static final long serialVersionUID = -2432898217068138400L;

	@Override
	protected void setup() {
		initializeGUI();
	}

	@Override
	protected void takeDown() {
		finalizeGUI();
	}

	private void initializeGUI() {
	}

	private void finalizeGUI() {
	}
}
