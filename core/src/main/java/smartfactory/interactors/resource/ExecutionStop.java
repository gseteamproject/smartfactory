package smartfactory.interactors.resource;

import jade.lang.acl.ACLMessage;
import smartfactory.interactors.Interactor;
import smartfactory.models.Event;
import smartfactory.utility.AgentDataStore;

public class ExecutionStop extends Interactor {

	public ExecutionStop(AgentDataStore agentDataStore) {
		super(agentDataStore);
	}

	public ACLMessage execute(ACLMessage msg) {
		ACLMessage response = agentDataStore.getActivityRequest().createReply();
		String result = msg.getContent();
		if (result.compareTo(Event.OPERATION_COMPLETED_SUCCESS) == 0) {
			response.setPerformative(ACLMessage.INFORM);
		} else if (result.compareTo(Event.OPERATION_COMPLETED_FAILURE) == 0) {
			response.setPerformative(ACLMessage.FAILURE);
		} else {
			response.setPerformative(ACLMessage.NOT_UNDERSTOOD);
		}
		agentDataStore.setActivityResult(response);
		return response;
	}
}
