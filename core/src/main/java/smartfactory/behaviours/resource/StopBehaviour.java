package smartfactory.behaviours.resource;

import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

// TODO : rename to ActivityResult or something like this
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

	@Override
	public void reset() {
		super.reset();

		// TODO: check if other behaviour requires to use reset
		isResultDetermined = false;
	}

	private static final long serialVersionUID = -794793647519609669L;
}
