package smartfactory.behaviours.machine;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import smartfactory.models.Machine;

public class Deadline extends WakerBehaviour {

	public Deadline(Agent a, long timeout) {
		super(a, timeout);
	}
	
	@Override
	protected void onWake() {
		Activity parent = (Activity) getParent();

		Machine machine = parent.getMachine();
		machine.terminate("operation-xxx");

		ACLMessage failure = parent.getRequest().createReply();
		failure.setPerformative(ACLMessage.FAILURE);

		parent.setResult(failure);		
	}

	private static final long serialVersionUID = 9050743659839198854L;
}
