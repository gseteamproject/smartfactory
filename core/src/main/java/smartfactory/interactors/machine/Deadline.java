package smartfactory.interactors.machine;

import jade.lang.acl.ACLMessage;
import smartfactory.dataStores.MachineDataStore;

public class Deadline extends MachineInteractor {

	public Deadline(MachineDataStore dataStore) {
		super(dataStore);
	}
	
	public ACLMessage execute(ACLMessage request) {
		dataStore.getMachine().terminate("operation-xxx");

		ACLMessage response = request.createReply();
		response.setPerformative(ACLMessage.FAILURE);

		return response;
	}
}
