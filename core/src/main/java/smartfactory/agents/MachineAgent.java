package smartfactory.agents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import smartfactory.behaviours.machine.ActivityResponder;
import smartfactory.dataStores.MachineDataStore;
import smartfactory.models.Machine;

public class MachineAgent extends SmartFactoryAgent {

	private MachineDataStore dataStore;

	@Override
	protected void initializeData() {
		dataStore = new MachineDataStore();
		dataStore.setMachine(createMachine());
	}

	public Machine createMachine() {
		return new Machine();
	}

	@Override
	protected void registerServices() {
		String[] agentServiceNames = dataStore.getMachine().getOperations();

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
		addBehaviour(new ActivityResponder(this, dataStore));

		/*
		 * TODO : remove addBehaviour(new AchieveREResponderInteractorBehaviour(this,
		 * new PerformServiceResponder(dataStore)));
		 */
	}

	private static final long serialVersionUID = -1254510527324190708L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
