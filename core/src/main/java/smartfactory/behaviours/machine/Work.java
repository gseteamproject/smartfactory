package smartfactory.behaviours.machine;

import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import smartfactory.models.Machine;

public class Work extends SimpleBehaviour {

	private Activity owner = null;

	public Work(Activity ownerActivity) {
		this.owner = ownerActivity;
	}

	@Override
	public void action() {
		Machine machine = owner.getMachine();
		machine.execute("operation-xxx");

		ACLMessage inform = owner.getRequest().createReply();
		inform.setPerformative(ACLMessage.INFORM);

		owner.setResult(inform);
	}

	@Override
	public boolean done() {
		return owner.getMachine().hasExecuted();
	}

	private static final long serialVersionUID = -3500469822678572098L;
}
