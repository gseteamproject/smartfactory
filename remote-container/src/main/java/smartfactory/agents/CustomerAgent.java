package smartfactory.agents;

import jade.core.Agent;
import smartfactory.views.CustomerView;

public class CustomerAgent extends Agent {

	CustomerView view;

	@Override
	protected void setup() {
		initializeGUI();
	}

	@Override
	protected void takeDown() {
		finalizeGUI();
	}

	private void initializeGUI() {
		view = new CustomerView();
		view.agent = this;
		view.setVisible(true);
	}

	private void finalizeGUI() {
		view.setVisible(false);
		view.dispose();
	}

	public void addBlock() {
	}

	private static final long serialVersionUID = -2432898217068138400L;
}
