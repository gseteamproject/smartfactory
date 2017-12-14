package smartfactory.agents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import smartfactory.behaviours.production.ProductionProvisioningResponderBehaviour;
import smartfactory.dataStores.ProductionDataStore;
import smartfactory.models.Production;
import smartfactory.platform.JADEPlatform;

// TODO : possibly merge with MachineAgent
public class ProductionAgent extends BaseAgent {

	// TODO : check if getter is better
	protected ProductionDataStore dataStore;

	@Override
	protected void initializeData() {
		dataStore = new ProductionDataStore();
		dataStore.setProduction(createProduction());
		dataStore.setAgentPlatform(new JADEPlatform(this));
	}

	public Production createProduction() {
		return new Production();
	}

	@Override
	protected void registerServices() {
		String[] agentServiceNames = dataStore.getProduction().getOperations();

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

		try {
			DFService.register(this, agentServicesDescription);
		} catch (FIPAException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	protected void deregisterServices() {
		try {
			DFService.deregister(this);
		} catch (FIPAException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	protected void initializeBehaviours() {
		addBehaviour(new ProductionProvisioningResponderBehaviour(dataStore));
	}

	private static final long serialVersionUID = 3383937592776609628L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
