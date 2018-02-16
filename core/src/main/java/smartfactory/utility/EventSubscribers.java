package smartfactory.utility;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.proto.SubscriptionResponder.Subscription;
import jade.proto.SubscriptionResponder.SubscriptionManager;

public class EventSubscribers implements SubscriptionManager {

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
		Iterator<Subscription> i = subscriptions.values().iterator();
		while (i.hasNext()) {
			Subscription s = (Subscription) i.next();
			ACLMessage notification = new ACLMessage(ACLMessage.INFORM);
			notification.setContent(event);
			s.notify(notification);
		}
	}
}
