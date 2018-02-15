package smartfactory.utility;

import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.proto.SubscriptionResponder.Subscription;
import jade.proto.SubscriptionResponder.SubscriptionManager;

public class EventSubscribers implements SubscriptionManager {

	@Override
	public boolean register(Subscription s) throws RefuseException, NotUnderstoodException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deregister(Subscription s) throws FailureException {
		// TODO Auto-generated method stub
		return false;
	}

}
