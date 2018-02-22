package smartfactory.agents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import smartfactory.behaviours.base.EventSubscriptionInitiatorBehaviour;
import smartfactory.behaviours.base.LaunchAgentBehaviour;
import smartfactory.configuration.AgentConfiguration;
import smartfactory.models.EventHandler;
import smartfactory.presenters.CustomerPresenter;

public class CustomerAgent extends BaseAgent {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
				logger.debug("order is completed");
				presenter.showOrderIsCompleted();
			}
		}));
	}

	private static final long serialVersionUID = -2432898217068138400L;
}
