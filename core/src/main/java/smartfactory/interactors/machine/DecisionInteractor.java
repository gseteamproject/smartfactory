package smartfactory.interactors.machine;

import jade.lang.acl.ACLMessage;
import smartfactory.dataStores.MachineDataStore;

public class DecisionInteractor extends MachineInteractor {

	public DecisionInteractor(MachineDataStore dataStore) {
		super(dataStore);
	}

	public ACLMessage execute(ACLMessage request) {
		ACLMessage response = request.createReply();
		if (dataStore.getMachine().willExecute("operation-xxx")) {
			response.setPerformative(ACLMessage.AGREE);

		} else {
			response.setPerformative(ACLMessage.REFUSE);
		}
		return response;
	}
}
