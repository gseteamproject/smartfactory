package salesMarketBehaviours;

import java.util.Vector;

import common.AgentDataStore;
import interactors.AchieveREInitiatorInteractor;
import interactors.ResponderBehaviour;
import jade.core.AID;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class AskForOrderInitiator extends AchieveREInitiatorInteractor {

	public AskForOrderInitiator(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour, dataStore);
	}

	@Override
	public Vector<ACLMessage> prepareRequests(ACLMessage request) {
		ACLMessage message = new ACLMessage(ACLMessage.REQUEST);

		String requestedAction = "Ask";
		message.addReceiver(new AID(("AgentSelling"), AID.ISLOCALNAME));
		setup(message, requestedAction, false);

		return l;
	}

	@Override
	public void handleInform(ACLMessage inform) {

		handleResponse(inform);

		interactionBehaviour.getAgent().addBehaviour(new TakeFromWarehouseBehaviour(interactionBehaviour, dataStore));
	}

	@Override
	public void handleFailure(ACLMessage failure) {

		handleResponse(failure);

		MessageTemplate temp = MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
		MessageTemplate infTemp = MessageTemplate.and(temp, MessageTemplate.MatchPerformative(ACLMessage.INFORM));
		infTemp = MessageTemplate.and(infTemp, MessageTemplate.MatchConversationId("Ask"));
	}

	@Override
	public void handleAgree(ACLMessage agree) {
	}

	@Override
	public void handleRefuse(ACLMessage refuse) {
	}

	@Override
	public int next() {
		return 0;
	}
}
