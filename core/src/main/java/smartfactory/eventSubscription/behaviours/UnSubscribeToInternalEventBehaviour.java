package smartfactory.eventSubscription.behaviours;

import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import smartfactory.eventSubscription.ontology.EventSubscriptionOntology;

public class UnSubscribeToInternalEventBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = 7050336236689395786L;

	@Override
	public void action() {
		// TODO: add corresponding Interactor
		ACLMessage subscription = new ACLMessage(ACLMessage.CANCEL);
		subscription.addReceiver(getAgent().getAID());
		subscription.setProtocol(FIPANames.InteractionProtocol.FIPA_SUBSCRIBE);
		subscription.setConversationId("self-messaging");
		subscription.setLanguage(FIPANames.ContentLanguage.FIPA_SL);
		subscription.setOntology(EventSubscriptionOntology.ONTOLOGY_NAME);
		getAgent().send(subscription);
	}
}
