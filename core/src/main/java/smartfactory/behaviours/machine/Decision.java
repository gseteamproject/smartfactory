package smartfactory.behaviours.machine;

import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import smartfactory.dataStores.MachineDataStore;

public class Decision extends OneShotBehaviour {

	MachineDataStore machineDataStore;

	public Decision(MachineDataStore machineDataStore) {
		this.machineDataStore = machineDataStore;
	}

	@Override
	public void action() {
		ActivityResponder parent = (ActivityResponder) getParent();
		ACLMessage request = (ACLMessage) getDataStore().get(parent.REQUEST_KEY);

		ACLMessage response = request.createReply();
		if (machineDataStore.getMachine().willExecute("operation-xxx")) {
			response.setPerformative(ACLMessage.AGREE);

		} else {
			response.setPerformative(ACLMessage.REFUSE);
		}

		getDataStore().put(parent.RESPONSE_KEY, response);
	}

	private static final long serialVersionUID = -7477532730880615646L;
}
