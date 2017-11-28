package smartfactory.agents;

import smartfactory.behaviours.customer.LaunchAgentBehaviour;
import smartfactory.configuration.AgentConfiguration;
import smartfactory.dataStores.CustomerDataStore;
import smartfactory.platform.JADEPlatform;
import smartfactory.presenters.CustomerPresenter;

public class CustomerAgent extends BaseAgent {

	private CustomerPresenter presenter = new CustomerPresenter(this);

	private CustomerDataStore dataStore;

	@Override
	protected void initializeData() {
		dataStore = new CustomerDataStore();
		dataStore.setAgentPlatform(new JADEPlatform(this));
	}

	@Override
	protected void initializeGUI() {
		presenter.show();
	}

	@Override
	protected void finalizeGUI() {
		presenter.hide();
	}

	public void createOrder() {
		AgentConfiguration agentConfiguration = new AgentConfiguration();
		agentConfiguration.name = OrderAgent.getUniqueName();
		agentConfiguration.className = OrderAgent.class.getName();
		dataStore.setAgentConfiguration(agentConfiguration);

		addBehaviour(new LaunchAgentBehaviour(dataStore));
	}

	private static final long serialVersionUID = -2432898217068138400L;
}
