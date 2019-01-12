package smartfactory.utility;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.proto.SubscriptionResponder.Subscription;
import jade.proto.SubscriptionResponder.SubscriptionManager;
import smartfactory.models.Event;
import smartfactory.ontology.EventSubscriptionOntology;

public class EventSubscribers implements SubscriptionManager {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private Map<String, Subscription> subscriptions = new HashMap<String, Subscription>();

	private AgentDataStore agentDataStore;

	public EventSubscribers(AgentDataStore agentDataStore) {
		this.agentDataStore = agentDataStore;
	}

	@Override
	public boolean register(Subscription s) throws RefuseException, NotUnderstoodException {
		subscriptions.put(s.getMessage().getConversationId(), s);
		return true;
	}

	@Override
	public boolean deregister(Subscription s) throws FailureException {
		subscriptions.remove(s.getMessage().getConversationId());
		return true;
	}

	public void notifyAll(Event event) {
		String id = event.getId();
		Subscription s = null;
		if (id.compareTo(Event.OPERATION_COMPLETED_SUCCESS) == 0) {
			s = subscriptions.get("self-messaging");
		} else if (id.compareTo(Event.OPERATION_COMPLETED_FAILURE) == 0) {
			s = subscriptions.get("self-messaging");
		} else if (id.compareTo(Event.PROCESS_COMPLETED_SUCCESS) == 0) {
			s = subscriptions.get("process-status");
		} else if (id.compareTo(Event.PROCESS_COMPLETED_FAILURE) == 0) {
			s = subscriptions.get("process-status");
		} else {
			s = subscriptions.get(id);
		}

		if (s != null) {
			ACLMessage notification = new ACLMessage(ACLMessage.INFORM);
			notification.setLanguage(FIPANames.ContentLanguage.FIPA_SL);
			notification.setOntology(EventSubscriptionOntology.ONTOLOGY_NAME);
			agentDataStore.getAgentPlatform().fillContent(notification, event);
			s.notify(notification);
		} else {
			logger.error("unregistered subscription for event \"{}\"", id);
		}
	}
}
