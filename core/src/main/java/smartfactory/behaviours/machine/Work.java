package smartfactory.behaviours.machine;

import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import smartfactory.dataStores.MachineDataStore;

public class Work extends SimpleBehaviour {

	private Activity owner = null;

	MachineDataStore dataStore;

	public Work(Activity ownerActivity, MachineDataStore machineDataStore) {
		this.owner = ownerActivity;
		this.dataStore = machineDataStore;
	}

	@Override
	public void action() {
		dataStore.getMachine().execute("operation-xxx");

		ACLMessage inform = owner.getRequest().createReply();
		inform.setPerformative(ACLMessage.INFORM);

		owner.setResult(inform);
	}

	@Override
	public boolean done() {
		return dataStore.getMachine().hasExecuted();
	}

	private static final long serialVersionUID = -3500469822678572098L;
}
