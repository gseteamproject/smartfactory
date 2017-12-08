package smartfactory.interactors.machine;

import jade.lang.acl.ACLMessage;
import smartfactory.dataStores.MachineDataStore;

public class Deadline extends MachineInteractor {

	public Deadline(MachineDataStore dataStore) {
		super(dataStore);
	}

	public ACLMessage execute(ACLMessage request) {
		// content = operation name
		String operationName = request.getContent();

		dataStore.getMachine().terminate(operationName);

		ACLMessage response = request.createReply();
		response.setPerformative(ACLMessage.FAILURE);

		return response;
	}
}
