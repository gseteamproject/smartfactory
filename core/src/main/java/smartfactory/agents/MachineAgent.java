package smartfactory.agents;

import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class MachineAgent extends SmartFactoryAgent {

	@Override
	protected void registerServices() {
		String[] agentServiceNames = getAgentServices();

		ServiceDescription agentServices[] = new ServiceDescription[agentServiceNames.length];
		for (int i = 0; i < agentServices.length; i++) {
			agentServices[i] = new ServiceDescription();
			agentServices[i].setName(agentServiceNames[i]);
			agentServices[i].setType("");
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

	public String[] getAgentServices() {
		return new String[0];
	}

	private static final long serialVersionUID = -1254510527324190708L;
}
