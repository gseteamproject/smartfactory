package smartfactory.utility;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.proto.SubscriptionResponder.Subscription;
import jade.proto.SubscriptionResponder.SubscriptionManager;

public class EventSubscribers implements SubscriptionManager {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private Map<String, Subscription> subscriptions = new HashMap<String, Subscription>();

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

	public void notifyAll(String event) {
		Subscription s = null;
		if (event.compareTo("operation-completed") == 0) {
			s = subscriptions.get("self-messaging");
		} else if (event.compareTo("process-completed") == 0) {
			s = subscriptions.get("process-status");
		} else {
			s = subscriptions.get(event);
		}

		if (s != null) {
			ACLMessage notification = new ACLMessage(ACLMessage.INFORM);
			notification.setContent(event);
			s.notify(notification);
		} else {
			logger.error("unregistered subscription for event \"{}\"", event);
		}

		// TODO : remove
		/*
		 * Iterator<Subscription> i = subscriptions.values().iterator(); while
		 * (i.hasNext()) { Subscription s = (Subscription) i.next(); ACLMessage
		 * notification = new ACLMessage(ACLMessage.INFORM);
		 * notification.setContent(event); s.notify(notification); }
		 */
	}
}
