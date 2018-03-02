package smartfactory.agents;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.core.Agent;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import smartfactory.behaviours.base.EventSubscriptionResponderBehaviour;
import smartfactory.behaviours.base.SelfSubscribeBehaviour;
import smartfactory.models.AgentService;
import smartfactory.platform.AgentPlatform;
import smartfactory.platform.JADEPlatform;
import smartfactory.utility.AgentDataStore;
import smartfactory.utility.AgentServices;
import smartfactory.utility.EventSubscribers;

public class BaseAgent extends Agent {

	private static final long serialVersionUID = -611439455095174043L;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected AgentDataStore agentDataStore = new AgentDataStore();

	protected AgentServices agentServices = new AgentServices();

	protected AgentPlatform agentPlatform = new JADEPlatform(this);

	@Override
	protected void setup() {
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

	@Override
	protected void takeDown() {
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
		agentDataStore.setAgent(this);
		agentDataStore.setAgentPlatform(agentPlatform);
		agentDataStore.setAgentServices(agentServices);
		agentDataStore.setEventSubscribers(new EventSubscribers());
	}

	protected void setupServices() {
		agentServices.addService(new AgentService("discovering", agentDataStore));
	}

	private void registerServices() {
		List<ServiceDescription> serviceDescriptions = agentServices.getServiceDescriptions();

		DFAgentDescription agentDescription = new DFAgentDescription();
		agentDescription.setName(getAID());
		for (ServiceDescription serviceDescription : serviceDescriptions) {
			agentDescription.addServices(serviceDescription);
			logger.info("providing \"{}\"", serviceDescription.getName());
		}

		agentPlatform.registerAgentServices(agentDescription);
	}

	private void deregisterServices() {
		agentPlatform.deregisterAgentServices();
	}
}
