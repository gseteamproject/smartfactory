package interactors;

import java.util.Vector;

import basicClasses.Order;
import common.AgentDataStore;
import communication.MessageObject;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import ontology.CompanyOntology;

public class RequestInteractor {

	protected AgentDataStore dataStore;

	protected ResponderBehaviour interactionBehaviour;

	protected String orderText;

	protected MessageObject msgObj;

	protected Vector<ACLMessage> l;

	public RequestInteractor(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		this.interactionBehaviour = interactionBehaviour;
		this.dataStore = dataStore;
	}

	protected void setup(ACLMessage request, String requestedAction, boolean isSub) {
		request.setConversationId(requestedAction);
		request.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
		request.setLanguage(FIPANames.ContentLanguage.FIPA_SL);
		request.setOntology(CompanyOntology.ONTOLOGY_NAME);
		if (isSub) {
			request.setContent(dataStore.getSubMessage().getContent());
		} else {
			request.setContent(interactionBehaviour.getRequest().getContent());
		}

		l = new Vector<ACLMessage>(1);
		l.addElement(request);
	}

	protected void handleResponse(ACLMessage message) {
		Order order = Order.fromJson(message.getContent());
		orderText = order.getTextOfOrder();

		msgObj = new MessageObject(message, orderText);
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);
	}
}
