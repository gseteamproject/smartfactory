package smartfactory.agents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import smartfactory.behaviours.StoreNetResponder;

public class WarehouseAgent extends SmartFactoryAgent {

	@Override
	protected void initializeBehaviours() {
		addBehaviour(new StoreNetResponder(this));
	}

	@Override
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

	@Override
	protected void deregisterServices() {
		try {
			DFService.deregister(this);
		} catch (FIPAException e) {
			logger.error("", e);
		}
	}

	private static final long serialVersionUID = -1157284189612879872L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
