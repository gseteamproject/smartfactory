package smartfactory.behaviours.machine;

import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.ThreadedBehaviourFactory;
import jade.lang.acl.ACLMessage;
import smartfactory.models.Machine;
import smartfactory.behaviours.machine.ActivityResponder;

public class Activity extends ParallelBehaviour {

	public Machine getMachine() {
		return (Machine) getDataStore().get("machine");
	}

	public void setResult(ACLMessage result) {
		ActivityResponder parent = (ActivityResponder) getParent();
		getDataStore().put(parent.RESULT_NOTIFICATION_KEY, result);
	}

	public ACLMessage getRequest() {
		ActivityResponder parent = (ActivityResponder) getParent();
		return (ACLMessage) getDataStore().get(parent.REQUEST_KEY);
	}

	private ThreadedBehaviourFactory tbf = new ThreadedBehaviourFactory();

	public Activity(Agent a) {
		super(a, WHEN_ANY);
		addSubBehaviour(tbf.wrap(new Work(this)));
		addSubBehaviour(new Status(a));
		addSubBehaviour(new Deadline(a, Machine.DURATION_LIMIT * 1000));
	}

	private static final long serialVersionUID = -987827733291008766L;
}
