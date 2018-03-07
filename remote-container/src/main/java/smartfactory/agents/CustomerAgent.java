package smartfactory.agents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.lang.acl.ACLMessage;
import smartfactory.behaviours.base.EventSubscriptionInitiatorBehaviour;
import smartfactory.behaviours.base.LaunchAgentBehaviour;
import smartfactory.configuration.AgentConfiguration;
import smartfactory.models.Event;
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

	public void createOrder() {
		String agentName = OrderProcessAgent.getUniqueName();
		String agentClass = OrderProcessAgent.class.getName();
		AgentConfiguration agentConfiguration = new AgentConfiguration(agentName, agentClass);
		agentDataStore.setSubAgentConfiguration(agentConfiguration);

		addBehaviour(new LaunchAgentBehaviour(agentDataStore));
		addBehaviour(new EventSubscriptionInitiatorBehaviour(agentName, "process-status", new EventHandler() {
			public void callback(ACLMessage message) {
				String result = message.getContent();
				if (result.compareTo(Event.PROCESS_COMPLETED_SUCCESS) == 0) {
					logger.debug("order is completed");
					presenter.showOrderIsCompleted();
				} else {
					logger.debug("order is failed");
					presenter.showOrderIsFailed();
				}
			}
		}));
	}

	private static final long serialVersionUID = -2432898217068138400L;
}
