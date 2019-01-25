package interactors;

import java.util.Vector;

import basicClasses.Order;
import common.AgentDataStore;
import communication.MessageObject;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

public class RequestInteractor {

	protected AgentDataStore dataStore;

	protected String orderText;

	protected MessageObject msgObj;

	protected Vector<ACLMessage> l;

	public RequestInteractor(AgentDataStore dataStore) {
		this.dataStore = dataStore;
	}

	protected void setup(ACLMessage request, String requestedAction, boolean isSub) {
		request.setConversationId(requestedAction);
		request.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
		if (isSub) {
			request.setContent(dataStore.getSubMessage().getContent());
		} else {
			request.setContent(dataStore.getRequestMessage().getContent());
		}

		l = new Vector<ACLMessage>(1);
		l.addElement(request);
	}

	protected void handleResponse(ACLMessage message) {
		Order order = Order.gson.fromJson(message.getContent(), Order.class);
		orderText = order.getTextOfOrder();

		msgObj = new MessageObject(message, orderText);
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);
	}
}
