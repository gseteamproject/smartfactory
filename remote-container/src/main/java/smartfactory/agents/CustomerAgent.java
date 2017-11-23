package smartfactory.agents;

import smartfactory.behaviours.LaunchAgentBehaviour;
import smartfactory.presenters.CustomerPresenter;

public class CustomerAgent extends BaseAgent {

	CustomerPresenter presenter = new CustomerPresenter(this);

	@Override
	protected void initializeGUI() {
		presenter.show();
	}

	@Override
	protected void finalizeGUI() {
		presenter.hide();
	}

	public void createOrder() {
		addBehaviour(new LaunchAgentBehaviour(this, OrderAgent.getUniqueName(), OrderAgent.class.getName()));
	}

	private static final long serialVersionUID = -2432898217068138400L;
}
