package smartfactory.behaviours.machine;

import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import smartfactory.dataStores.MachineDataStore;

public class WorkBehaviour extends SimpleBehaviour {

	ActivityResponderBehaviour interactionBehaviour;

	MachineDataStore dataStore;

	public WorkBehaviour(ActivityResponderBehaviour interactionBehaviour, MachineDataStore machineDataStore) {
		this.interactionBehaviour = interactionBehaviour;
		this.dataStore = machineDataStore;
	}

	@Override
	public void action() {
		dataStore.getMachine().execute("operation-xxx");

		ACLMessage inform = interactionBehaviour.getRequest().createReply();
		inform.setPerformative(ACLMessage.INFORM);

		interactionBehaviour.setResult(inform);
	}

	@Override
	public boolean done() {
		return dataStore.getMachine().hasExecuted();
	}

	private static final long serialVersionUID = -3500469822678572098L;
}
