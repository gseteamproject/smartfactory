package smartfactory.behaviours.base;

import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.proto.SubscriptionResponder;

public class EventSubscriptionResponderBehaviour extends SubscriptionResponder {

	private static final long serialVersionUID = 7968922573963662440L;

	public EventSubscriptionResponderBehaviour(SubscriptionManager manager) {
		super(null, SubscriptionResponder.createMessageTemplate(ACLMessage.SUBSCRIBE), manager);
	}

	@Override
	protected ACLMessage handleSubscription(ACLMessage subscription) {
		ACLMessage response = subscription.createReply();
		try {
			super.handleSubscription(subscription);
			response.setPerformative(ACLMessage.AGREE);
		} catch (RefuseException e) {
			response.setPerformative(ACLMessage.REFUSE);
		} catch (NotUnderstoodException e) {
			response.setPerformative(ACLMessage.NOT_UNDERSTOOD);
		}
		return response;
	}

	@Override
	protected ACLMessage handleCancel(ACLMessage cancel) {
		ACLMessage response = cancel.createReply();
		try {
			super.handleCancel(cancel);
			response.setPerformative(ACLMessage.INFORM);
		} catch (FailureException e) {
			response.setPerformative(ACLMessage.FAILURE);
		}
		return response;
	}
}
