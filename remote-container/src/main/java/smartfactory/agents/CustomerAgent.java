package smartfactory.agents;

import smartfactory.behaviours.base.LaunchAgentBehaviour;
import smartfactory.configuration.AgentConfiguration;
import smartfactory.presenters.CustomerPresenter;

public class CustomerAgent extends BaseAgent {

	private CustomerPresenter presenter = new CustomerPresenter(this);

	@Override
	protected void setupData() {
		super.setupData();
	}

	@Override
	protected void setupGUI() {
		presenter.show();
	}

	@Override
	protected void takeDownGUI() {
		presenter.hide();
	}

	public void createOrder() {
		AgentConfiguration subAgentConfiguration = new AgentConfiguration();
		subAgentConfiguration.name = OrderProcessAgent.getUniqueName();
		subAgentConfiguration.className = OrderProcessAgent.class.getName();
		agentDataStore.setSubAgentConfiguration(subAgentConfiguration);

		addBehaviour(new LaunchAgentBehaviour(agentDataStore));
	}

	private static final long serialVersionUID = -2432898217068138400L;
}
