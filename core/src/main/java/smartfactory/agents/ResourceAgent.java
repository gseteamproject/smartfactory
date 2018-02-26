package smartfactory.agents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import smartfactory.behaviours.resource.ServiceProvisioningResponderBehaviour;
import smartfactory.models.Resource;

public class ResourceAgent extends BaseAgent {

	private static final long serialVersionUID = -1254510527324190708L;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	final protected void setupData() {
		super.setupData();
		Resource resource = createResource();
		resource.setEventSubscribers(agentDataStore.getEventSubsribers());
		agentDataStore.setResource(resource);
	}

	public Resource createResource() {
		return new Resource();
	}

	@Override
	final protected void registerServices() {
		String[] agentServiceNames = agentDataStore.getResource().getOperations();

		ServiceDescription agentServices[] = new ServiceDescription[agentServiceNames.length];
		for (int i = 0; i < agentServices.length; i++) {
			agentServices[i] = new ServiceDescription();
			agentServices[i].setName(agentServiceNames[i]);
			agentServices[i].setType("");
			logger.info("providing \"{}\"", agentServiceNames[i]);
		}

		DFAgentDescription agentServicesDescription = new DFAgentDescription();
		agentServicesDescription.setName(getAID());
		for (ServiceDescription agentService : agentServices) {
			agentServicesDescription.addServices(agentService);
		}

		agentDataStore.getAgentPlatform().registerAgentServices(agentServicesDescription);
	}

	@Override
	final protected void deregisterServices() {
		agentDataStore.getAgentPlatform().deregisterAgentServices();
	}

	@Override
	protected void setupBehaviours() {
		super.setupBehaviours();
		addBehaviour(new ServiceProvisioningResponderBehaviour(agentDataStore));
	}
}
