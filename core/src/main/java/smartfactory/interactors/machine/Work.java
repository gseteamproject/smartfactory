package smartfactory.interactors.machine;

import jade.lang.acl.ACLMessage;
import smartfactory.dataStores.MachineDataStore;

public class Work extends MachineInteractor {

	public Work(MachineDataStore dataStore) {
		super(dataStore);
	}

	public ACLMessage execute(ACLMessage request) {
		// content = operation name
		String operationName = request.getContent();

		dataStore.getMachine().execute(operationName);

		ACLMessage response = request.createReply();
		response.setPerformative(ACLMessage.INFORM);

		return response;
	}

	public boolean done() {
		return dataStore.getMachine().hasExecuted();
	}
}
