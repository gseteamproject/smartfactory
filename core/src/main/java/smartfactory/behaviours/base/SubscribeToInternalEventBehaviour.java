package smartfactory.behaviours.base;

import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

public class SubscribeToInternalEventBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = -798743164775649606L;

	@Override
	public void action() {
		ACLMessage subscription = new ACLMessage(ACLMessage.SUBSCRIBE);
		subscription.addReceiver(getAgent().getAID());
		subscription.setProtocol(FIPANames.InteractionProtocol.FIPA_SUBSCRIBE);
		subscription.setConversationId("self-messaging");
		getAgent().send(subscription);
	}
}
