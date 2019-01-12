package smartfactory.behaviours.base;

import java.util.Vector;

import jade.core.AID;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.proto.SubscriptionInitiator;
import smartfactory.ontology.EventSubscriptionOntology;

public class SubscribeToEventInitiatorBehaviour extends SubscriptionInitiator {

	private static final long serialVersionUID = -2386799595521331797L;

	// TODO : refactor responderAgent

	String responderAgent;

	String event;

	EventHandler callback;

	public static interface EventHandler {
		public void callback(ACLMessage message);
	}

	public SubscribeToEventInitiatorBehaviour(String responderAgent, String event, EventHandler callback) {
		super(null, null);
		this.responderAgent = responderAgent;
		this.event = event;
		this.callback = callback;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected Vector prepareSubscriptions(ACLMessage subscription) {
		ACLMessage message = new ACLMessage(ACLMessage.SUBSCRIBE);
		message.addReceiver(new AID((responderAgent), AID.ISLOCALNAME));
		message.setProtocol(FIPANames.InteractionProtocol.FIPA_SUBSCRIBE);
		message.setLanguage(FIPANames.ContentLanguage.FIPA_SL);
		message.setOntology(EventSubscriptionOntology.ONTOLOGY_NAME);
		message.setConversationId(event);
		Vector l = new Vector(1);
		l.addElement(message);
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
			callback.callback(inform);

			cancel(inform.getSender(), false);
			// TODO: check if subscription cancelled after first notification
			cancel_inform = true;
		}
	}
}
