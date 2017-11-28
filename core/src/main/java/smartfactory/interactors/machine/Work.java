package smartfactory.interactors.machine;

import jade.lang.acl.ACLMessage;
import smartfactory.dataStores.MachineDataStore;

public class Work extends MachineInteractor {

	public Work(MachineDataStore dataStore) {
		super(dataStore);
	}

	public ACLMessage execute(ACLMessage request) {
		dataStore.getMachine().execute("operation-xxx");

		ACLMessage response = request.createReply();
		response.setPerformative(ACLMessage.INFORM);

		return response;
	}

	public boolean done() {
		return dataStore.getMachine().hasExecuted();
	}
}
