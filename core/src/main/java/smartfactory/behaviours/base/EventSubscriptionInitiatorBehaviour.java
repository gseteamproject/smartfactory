package smartfactory.behaviours.base;

import java.util.Vector;

import jade.core.AID;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.proto.SubscriptionInitiator;
import smartfactory.models.EventHandler;

public class EventSubscriptionInitiatorBehaviour extends SubscriptionInitiator {

	private static final long serialVersionUID = -2386799595521331797L;

	// TODO : replace ResourceOperation with Interactors mechanics
	// TODO : refactor responderAgent

	String responderAgent;

	String event;

	EventHandler callback;

	public EventSubscriptionInitiatorBehaviour(String responderAgent, String event, EventHandler callback) {
		super(null, null);
		this.responderAgent = responderAgent;
		this.event = event;
		this.callback = callback;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected Vector prepareSubscriptions(ACLMessage subscription) {
		subscription = new ACLMessage(ACLMessage.SUBSCRIBE);
		subscription.addReceiver(new AID((responderAgent), AID.ISLOCALNAME));
		subscription.setProtocol(FIPANames.InteractionProtocol.FIPA_SUBSCRIBE);
		subscription.setConversationId(event);
		Vector l = new Vector(1);
		l.addElement(subscription);
		return l;
	}

	@Override
	protected void handleAgree(ACLMessage agree) {
	}

	@Override
	protected void handleRefuse(ACLMessage refuse) {
	}

	boolean cancel_inform = false;

	@Override
	protected void handleInform(ACLMessage inform) {
		if (cancel_inform) {
			cancellationCompleted(inform.getSender());
			myAgent.removeBehaviour(this);
		} else {
			// actions implemented in resource operation
			callback.callback();

			cancel(inform.getSender(), false);
			cancel_inform = true;
		}
	}
}
