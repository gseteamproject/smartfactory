package smartfactory.behaviours.machine;

import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.ThreadedBehaviourFactory;
import jade.lang.acl.ACLMessage;
import smartfactory.behaviours.machine.ActivityResponder;
import smartfactory.dataStores.MachineDataStore;

public class Activity extends ParallelBehaviour {

	public void setResult(ACLMessage result) {
		ActivityResponder parent = (ActivityResponder) getParent();
		getDataStore().put(parent.RESULT_NOTIFICATION_KEY, result);
	}

	public ACLMessage getRequest() {
		ActivityResponder parent = (ActivityResponder) getParent();
		return (ACLMessage) getDataStore().get(parent.REQUEST_KEY);
	}

	private ThreadedBehaviourFactory tbf = new ThreadedBehaviourFactory();

	public Activity(Agent a, MachineDataStore machineDataStore) {
		super(a, WHEN_ANY);
		addSubBehaviour(tbf.wrap(new Work(this, machineDataStore)));
		addSubBehaviour(new Status(a, machineDataStore));
		addSubBehaviour(new Deadline(a, machineDataStore));
	}

	private static final long serialVersionUID = -987827733291008766L;
}
