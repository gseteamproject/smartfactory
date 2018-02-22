package smartfactory.agents;

import smartfactory.behaviours.base.EventSubscriptionInitiatorBehaviour;
import smartfactory.behaviours.base.LaunchAgentBehaviour;
import smartfactory.configuration.AgentConfiguration;
import smartfactory.models.EventHandler;
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

	String processAgentName;

	public void createOrder() {
		processAgentName = OrderProcessAgent.getUniqueName();
		AgentConfiguration subAgentConfiguration = new AgentConfiguration();
		subAgentConfiguration.name = processAgentName;
		subAgentConfiguration.className = OrderProcessAgent.class.getName();
		agentDataStore.setSubAgentConfiguration(subAgentConfiguration);

		addBehaviour(new LaunchAgentBehaviour(agentDataStore));
		addBehaviour(new EventSubscriptionInitiatorBehaviour(processAgentName, "process-status", new EventHandler() {
			@Override
			public void callback() {
				// TODO : show on the GUI that order is completed
				presenter.showOrderIsCompleted();
			}
		}));
	}

	private static final long serialVersionUID = -2432898217068138400L;
}
