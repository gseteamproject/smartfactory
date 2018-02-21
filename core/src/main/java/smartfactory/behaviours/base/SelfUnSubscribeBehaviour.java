package smartfactory.behaviours.base;

import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

public class SelfUnSubscribeBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = 7050336236689395786L;

	@Override
	public void action() {
		ACLMessage subscription = new ACLMessage(ACLMessage.CANCEL);
		subscription.addReceiver(getAgent().getAID());
		subscription.setProtocol(FIPANames.InteractionProtocol.FIPA_SUBSCRIBE);
		subscription.setConversationId("self-messaging");
		getAgent().send(subscription);
	}
}