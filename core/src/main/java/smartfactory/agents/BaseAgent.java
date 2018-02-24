package smartfactory.agents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.core.Agent;
import smartfactory.behaviours.base.EventSubscriptionResponderBehaviour;
import smartfactory.behaviours.base.SelfSubscribeBehaviour;
import smartfactory.platform.JADEPlatform;
import smartfactory.utility.AgentDataStore;
import smartfactory.utility.EventSubscribers;

public class BaseAgent extends Agent {

	private static final long serialVersionUID = -611439455095174043L;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected AgentDataStore agentDataStore;

	@Override
	final protected void setup() {
		setupData();
		logger.debug("data setup completed");

		setupGUI();
		logger.debug("GUI setup completed");

		setupBehaviours();
		logger.debug("behaviours setup completed");

		setupLanguage();
		logger.debug("language setup completed");

		registerServices();
		logger.debug("services registered");

		logger.info("setup completed");
	}

	@Override
	final protected void takeDown() {
		deregisterServices();
		logger.debug("services deregistered");

		takeDownGUI();
		logger.debug("GUI takeDown completed");

		logger.info("takeDown completed");
	}

	protected void setupBehaviours() {
		addBehaviour(new EventSubscriptionResponderBehaviour(agentDataStore.getEventSubsribers()));
		addBehaviour(new SelfSubscribeBehaviour());
	}

	protected void setupGUI() {
	}

	protected void takeDownGUI() {
	}

	protected void setupData() {
		agentDataStore = new AgentDataStore();
		agentDataStore.setAgentPlatform(new JADEPlatform(this));
		agentDataStore.setEventSubscribers(new EventSubscribers());
	}

	protected void setupLanguage() {
	}

	protected void registerServices() {
	}

	protected void deregisterServices() {
	}
}
