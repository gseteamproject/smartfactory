package smartfactory.behaviours.resource;

import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import smartfactory.behaviours.base.ServiceProvisioningResponderBehaviour;
import smartfactory.interactors.resource.ExecutionStop;
import smartfactory.utility.AgentDataStore;

public class ExecutionStopBehaviour extends SimpleBehaviour {

	private boolean shouldStop;

	ExecutionBehaviour activityBehaviour;

	ServiceProvisioningResponderBehaviour interactionBehaviour;

	ExecutionStop interactor;

	public ExecutionStopBehaviour(ExecutionBehaviour activityBehaviour,
			ServiceProvisioningResponderBehaviour interactionBehaviour, AgentDataStore agentDataStore) {
		this.activityBehaviour = activityBehaviour;
		this.interactionBehaviour = interactionBehaviour;
		this.interactor = new ExecutionStop(agentDataStore);
	}

	@Override
	public void action() {
		MessageTemplate matchConversationId = MessageTemplate.MatchConversationId("self-messaging");
		MessageTemplate matchPerformative = MessageTemplate.MatchPerformative(ACLMessage.INFORM);

		ACLMessage msg = myAgent.receive(MessageTemplate.and(matchConversationId, matchPerformative));
		if (msg != null) {
			interactionBehaviour.setResult(interactor.execute(msg));
			activityBehaviour.stop();
			shouldStop = true;
		} else {
			block();
		}
	}

	@Override
	public boolean done() {
		return shouldStop;
	}

	@Override
	public void reset() {
		super.reset();
		shouldStop = false;
	}

	private static final long serialVersionUID = -794793647519609669L;
}
