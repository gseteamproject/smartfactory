package basicAgents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import interactors.OrderDataStore;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import sellerBehaviours.RespondToBuy;

public class SellerAgent extends Agent {

	private static final long serialVersionUID = -7418692714860762106L;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected OrderDataStore dataStore;

	@Override
	protected void setup() {
		dataStore = new OrderDataStore();
		dataStore.setGoodName(getArguments()[0].toString());

		registerServices();

		addBehaviour(new RespondToBuy(this, dataStore));
	}

	private void registerServices() {
		ServiceDescription serviceDescription = new ServiceDescription();
		serviceDescription.setName(dataStore.getGoodName());
		serviceDescription.setType("procurement-service");
		DFAgentDescription agentDescription = new DFAgentDescription();
		agentDescription.setName(getAID());
		agentDescription.addServices(serviceDescription);
		try {
			DFService.register(this, agentDescription);
		} catch (FIPAException exception) {
			logger.error("register failed", exception);
		}
	}

	@Override
	protected void takeDown() {
		deregisterServices();
	}

	private void deregisterServices() {
		try {
			DFService.deregister(this);
		} catch (FIPAException exception) {
			logger.error("deregister failed", exception);
		}
	}
}
