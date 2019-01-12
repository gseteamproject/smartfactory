package smartfactory.behaviours.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.SubscriptionResponder;
import smartfactory.ontology.EventSubscriptionOntology;
import smartfactory.utility.AgentDataStore;

public class SubscribeToEventResponderBehaviour extends SubscriptionResponder {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final long serialVersionUID = 7968922573963662440L;

	public SubscribeToEventResponderBehaviour(AgentDataStore agentDataStore) {
		super(null,
				MessageTemplate.and(SubscriptionResponder.createMessageTemplate(ACLMessage.SUBSCRIBE),
						MessageTemplate.MatchOntology(EventSubscriptionOntology.ONTOLOGY_NAME)),
				agentDataStore.getEventSubsribers());
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
		logger.debug("subscription \"{}\" handled", subscription.getConversationId());
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
		logger.debug("subscription \"{}\" canceled", cancel.getConversationId());
		return response;
	}
}
