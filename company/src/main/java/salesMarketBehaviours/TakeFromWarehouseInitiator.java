package salesMarketBehaviours;

import java.util.Vector;

import interactors.AchieveREInitiatorInteractor;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;
import jade.core.AID;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

public class TakeFromWarehouseInitiator extends AchieveREInitiatorInteractor {

	private ResponderBehaviour interactionBehaviour;

	public TakeFromWarehouseInitiator(ResponderBehaviour interactionBehaviour, OrderDataStore dataStore) {
		super(dataStore);
		this.interactionBehaviour = interactionBehaviour;
	}

	@Override
	public Vector<ACLMessage> prepareRequests(ACLMessage request) {
		ACLMessage message = new ACLMessage(ACLMessage.REQUEST);

		String requestedAction = "Take";
		message.setConversationId(requestedAction);
		message.addReceiver(new AID(("AgentSelling"), AID.ISLOCALNAME));
		message.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
		message.setContent(dataStore.getRequestMessage().getContent());

		Vector<ACLMessage> l = new Vector<ACLMessage>(1);
		l.addElement(message);
		return l;
	}

	@Override
	public void handleInform(ACLMessage inform) {

		handleResponse(inform);

		interactionBehaviour.getAgent().addBehaviour(new DeliverToCustomerBehaviour(interactionBehaviour, dataStore));
	}

	@Override
	public void handleFailure(ACLMessage failure) {

		handleResponse(failure);
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
