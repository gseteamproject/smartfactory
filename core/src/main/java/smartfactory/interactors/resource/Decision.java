package smartfactory.interactors.resource;

import jade.lang.acl.ACLMessage;
import smartfactory.interactors.Interactor;
import smartfactory.utility.AgentDataStore;

public class Decision extends Interactor {

	public Decision(AgentDataStore dataStore) {
		super(dataStore);
	}

	public ACLMessage execute(ACLMessage request) {
		// content = operation name
		String serviceName = request.getContent();

		ACLMessage response = request.createReply();
		if (agentDataStore.getAgentServices().willExecuteService(serviceName)) {
			response.setPerformative(ACLMessage.AGREE);

		} else {
			response.setPerformative(ACLMessage.REFUSE);
		}
		return response;
	}
}
