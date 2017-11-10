package smartfactory.behaviours.machine;

import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import smartfactory.dataStores.MachineDataStore;
import smartfactory.models.Machine;

public class Deadline extends WakerBehaviour {

	MachineDataStore dataStore;
	ActivityResponder interactionBehaviour;

	public Deadline(ActivityResponder interactionBehaviour, MachineDataStore machineDataStore) {
		super(interactionBehaviour.getAgent(), Machine.DURATION_LIMIT * 1000);
		this.interactionBehaviour = interactionBehaviour;
		this.dataStore = machineDataStore;
	}

	@Override
	protected void onWake() {
		dataStore.getMachine().terminate("operation-xxx");

		ACLMessage failure = interactionBehaviour.getRequest().createReply();
		failure.setPerformative(ACLMessage.FAILURE);

		interactionBehaviour.setResult(failure);
	}

	private static final long serialVersionUID = 9050743659839198854L;
}
