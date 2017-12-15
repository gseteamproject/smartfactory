package smartfactory.behaviours.machine;

import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class StopBehaviour extends SimpleBehaviour {

	private boolean isResultDetermined = false;

	ActivityBehaviour activityBehaviour;

	public StopBehaviour(ActivityBehaviour activityBehaviour) {
		this.activityBehaviour = activityBehaviour;
	}

	@Override
	public void action() {
		// TODO : message must have interaction-id
		ACLMessage msg = myAgent.receive(MessageTemplate.MatchConversationId("activity-completed"));
		if (msg != null) {
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

	private static final long serialVersionUID = -794793647519609669L;
}
