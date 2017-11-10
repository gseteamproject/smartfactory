package smartfactory.behaviours.machine;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import smartfactory.dataStores.MachineDataStore;
import smartfactory.models.Machine;

public class Deadline extends WakerBehaviour {

	MachineDataStore dataStore;

	public Deadline(Agent a, MachineDataStore machineDataStore) {
		super(a, Machine.DURATION_LIMIT * 1000);
		this.dataStore = machineDataStore;
	}

	@Override
	protected void onWake() {
		Activity parent = (Activity) getParent();

		dataStore.getMachine().terminate("operation-xxx");

		ACLMessage failure = parent.getRequest().createReply();
		failure.setPerformative(ACLMessage.FAILURE);

		parent.setResult(failure);
	}

	private static final long serialVersionUID = 9050743659839198854L;
}
