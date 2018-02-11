package smartfactory.agents;

import smartfactory.behaviours.base.LaunchAgentBehaviour;
import smartfactory.configuration.AgentConfiguration;
import smartfactory.dataStores.CustomerDataStore;
import smartfactory.platform.JADEPlatform;
import smartfactory.presenters.CustomerPresenter;

public class CustomerAgent extends BaseAgent {

	private CustomerPresenter presenter = new CustomerPresenter(this);

	private CustomerDataStore dataStore;

	@Override
	protected void setupData() {
		dataStore = new CustomerDataStore();
		dataStore.setAgentPlatform(new JADEPlatform(this));
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
		dataStore.setSubAgentConfiguration(subAgentConfiguration);

		addBehaviour(new LaunchAgentBehaviour(dataStore));
	}

	private static final long serialVersionUID = -2432898217068138400L;
}
