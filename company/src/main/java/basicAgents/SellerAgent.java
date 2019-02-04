package basicAgents;

import common.AgentDataStore;
import common.AgentPlatform;
import jade.core.Agent;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import sellerBehaviours.RespondToBuy;

public class SellerAgent extends Agent {

	private static final long serialVersionUID = -7418692714860762106L;

	protected AgentDataStore dataStore;

	@Override
	protected void setup() {
		dataStore = new AgentDataStore();
		dataStore.setAgentPlatform(new AgentPlatform(this));
		dataStore.setAgentName(getLocalName());
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

		dataStore.getAgentPlatform().registerAgentServices(agentDescription);
	}

	@Override
	protected void takeDown() {
		dataStore.getAgentPlatform().deregisterAgentServices();
	}
}
