package smartfactory.agents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import smartfactory.behaviours.base.ServiceProvisioningResponderBehaviour;
import smartfactory.behaviours.base.SubscribeToEventResponderBehaviour;
import smartfactory.behaviours.base.SubscribeToInternalEventBehaviour;
import smartfactory.configuration.AgentConfiguration;
import smartfactory.models.AgentService;
import smartfactory.ontology.SmartfactoryOntology;
import smartfactory.services.Services;
import smartfactory.utility.AgentDataStore;

public class BaseAgent extends Agent {

	private static final long serialVersionUID = -611439455095174043L;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected AgentDataStore agentDataStore = new AgentDataStore(this);

	@Override
	protected void setup() {
		registerLanguage();
		logger.debug("language registered");

		registerOntology();
		logger.debug("ontology registered");

		setupData();
		logger.debug("data setup completed");

		setupServices();
		logger.debug("services setup completed");

		setupGUI();
		logger.debug("GUI setup completed");

		registerServices();
		logger.debug("services registered");

		setupBehaviours();
		logger.debug("behaviours setup completed");

		logger.info("setup completed");
	}

	protected void registerLanguage() {
		getContentManager().registerLanguage(new SLCodec());
	}

	protected void registerOntology() {
		getContentManager().registerOntology(SmartfactoryOntology.getInstance());
	}

	@Override
	protected void takeDown() {
		deregisterServices();
		logger.debug("services deregistered");

		takeDownGUI();
		logger.debug("GUI takeDown completed");

		logger.info("takeDown completed");
	}

	protected void setupBehaviours() {
		addBehaviour(new ServiceProvisioningResponderBehaviour(agentDataStore));
		addBehaviour(new SubscribeToEventResponderBehaviour(agentDataStore));
		addBehaviour(new SubscribeToInternalEventBehaviour());
	}

	protected void setupGUI() {
	}

	protected void takeDownGUI() {
	}

	protected void setupData() {
	}

	protected void setupServices() {
		agentDataStore.getAgentServices().addService(new AgentService(Services.discovering, agentDataStore));
	}

	private void registerServices() {
		agentDataStore.getAgentServices().registerAgentServices();
	}

	private void deregisterServices() {
		agentDataStore.getAgentServices().deregisterAgentServices();
	}

	public AgentConfiguration getAgentConfiguration() {
		return (AgentConfiguration) getArguments()[0];
	}
}
