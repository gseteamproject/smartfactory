package smartfactory.behaviours.machine;

import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import smartfactory.dataStores.MachineDataStore;

public class Decision extends OneShotBehaviour {

	MachineDataStore machineDataStore;

	ActivityResponder interactionBehaviour;

	public Decision(ActivityResponder interactionBehaviour, MachineDataStore machineDataStore) {
		this.machineDataStore = machineDataStore;
		this.interactionBehaviour = interactionBehaviour;
	}

	@Override
	public void action() {
		ACLMessage request = interactionBehaviour.getRequest();

		ACLMessage response = request.createReply();
		if (machineDataStore.getMachine().willExecute("operation-xxx")) {
			response.setPerformative(ACLMessage.AGREE);

		} else {
			response.setPerformative(ACLMessage.REFUSE);
		}

		interactionBehaviour.setResponse(response);
	}

	private static final long serialVersionUID = -7477532730880615646L;
}
