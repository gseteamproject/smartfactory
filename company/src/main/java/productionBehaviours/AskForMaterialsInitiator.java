package productionBehaviours;

import java.util.Vector;

import basicClasses.Order;
import common.AgentDataStore;
import communication.MessageObject;
import interactors.AchieveREInitiatorInteractor;
import interactors.ResponderBehaviour;
import jade.core.AID;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class AskForMaterialsInitiator extends AchieveREInitiatorInteractor {

	private MessageObject msgObj;

	public AskForMaterialsInitiator(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour, dataStore);
	}

	@Override
	public Vector<ACLMessage> prepareRequests(ACLMessage request) {
		ACLMessage message = new ACLMessage(ACLMessage.REQUEST);

		String requestedAction = "Materials";
		message.addReceiver(new AID(("AgentProcurement"), AID.ISLOCALNAME));
		setup(message, requestedAction, false);

		return l;
	}

	@Override
	public void handleInform(ACLMessage inform) {
		orderText = Order.fromJson(inform.getContent()).getTextOfOrder();

		msgObj = new MessageObject(inform, "received [inform] materials for " + orderText + " are in storage");
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);
		interactionBehaviour.getAgent().addBehaviour(new TakeFromStorageBehaviour(interactionBehaviour, dataStore));
	}

	@Override
	public void handleFailure(ACLMessage failure) {
		orderText = Order.fromJson(failure.getContent()).getTextOfOrder();

		msgObj = new MessageObject(failure, "received [failure] materials for " + orderText + " are not in storage");
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

		MessageTemplate temp = MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
		MessageTemplate infTemp = MessageTemplate.and(temp, MessageTemplate.MatchPerformative(ACLMessage.INFORM));
		infTemp = MessageTemplate.and(infTemp, MessageTemplate.MatchConversationId("Materials"));
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
