package smartfactory.behaviours.resource;

import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import smartfactory.models.Event;
import smartfactory.utility.AgentDataStore;

// TODO : rename to ActivityResult or something like this
public class StopBehaviour extends SimpleBehaviour {

	private boolean isResultDetermined = false;

	ActivityBehaviour activityBehaviour;

	ServiceProvisioningResponderBehaviour interactionBehaviour;

	AgentDataStore dataStore;

	public StopBehaviour(ActivityBehaviour activityBehaviour,
			ServiceProvisioningResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		this.activityBehaviour = activityBehaviour;
		this.interactionBehaviour = interactionBehaviour;
		this.dataStore = dataStore;
	}

	@Override
	public void action() {
		MessageTemplate matchConversationId = MessageTemplate.MatchConversationId("self-messaging");
		MessageTemplate matchPerformative = MessageTemplate.MatchPerformative(ACLMessage.INFORM);

		ACLMessage msg = myAgent.receive(MessageTemplate.and(matchConversationId, matchPerformative));
		if (msg != null) {
			ACLMessage response = dataStore.getActivityRequest().createReply();
			String result = msg.getContent();
			if (result.compareTo(Event.OPERATION_COMPLETED_SUCCESS) == 0) {
				response.setPerformative(ACLMessage.INFORM);
			} else if (result.compareTo(Event.OPERATION_COMPLETED_FAILURE) == 0) {
				response.setPerformative(ACLMessage.FAILURE);
			} else {
				response.setPerformative(ACLMessage.NOT_UNDERSTOOD);
			}
			dataStore.setActivityResult(response);

			interactionBehaviour.setResult(dataStore.getActivityResult());
			activityBehaviour.stop();
			isResultDetermined = true;
		} else {
			block();
		}
	}

	@Override
	public boolean done() {
		return isResultDetermined;
	}

	@Override
	public void reset() {
		super.reset();
		isResultDetermined = false;
	}

	private static final long serialVersionUID = -794793647519609669L;
}
