package smartfactory.behaviours.base;

import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import smartfactory.ontology.EventSubscriptionOntology;

public class SubscribeToInternalEventBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = -798743164775649606L;

	@Override
	public void action() {
		ACLMessage subscription = new ACLMessage(ACLMessage.SUBSCRIBE);
		subscription.addReceiver(getAgent().getAID());
		subscription.setProtocol(FIPANames.InteractionProtocol.FIPA_SUBSCRIBE);
		subscription.setConversationId("self-messaging");
		subscription.setLanguage(FIPANames.ContentLanguage.FIPA_SL);
		subscription.setOntology(EventSubscriptionOntology.ONTOLOGY_NAME);
		getAgent().send(subscription);
	}
}
