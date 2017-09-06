package smartfactory.agents;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class WarehouseAgent extends Agent {

	@Override
	protected void setup() {
		registerServices();
	}

	@Override
	protected void takeDown() {
		deregisterServices();
	}

	protected void registerServices() {
		ServiceDescription serviceDescription = new ServiceDescription();
		serviceDescription.setName("store");
		serviceDescription.setType("warehouse");

		DFAgentDescription agentDescription = new DFAgentDescription();
		agentDescription.setName(getAID());
		agentDescription.addServices(serviceDescription);

		try {
			DFService.register(this, agentDescription);
		} catch (FIPAException exception) {
			exception.printStackTrace();
		}
	}

	protected void deregisterServices() {
		try {
			DFService.deregister(this);
		} catch (FIPAException exception) {
			exception.printStackTrace();
		}
	}

	private static final long serialVersionUID = -1157284189612879872L;
}
