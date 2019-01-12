package smartfactory.serviceProvisioning.interactors;

import jade.lang.acl.ACLMessage;
import smartfactory.interactors.Interactor;
import smartfactory.models.Event;
import smartfactory.serviceProvisioning.ontology.ServiceCompleted;
import smartfactory.serviceProvisioning.ontology.ServiceFailed;
import smartfactory.utility.AgentDataStore;

public class ExecutionStop extends Interactor {

	public ExecutionStop(AgentDataStore agentDataStore) {
		super(agentDataStore);
	}

	public ACLMessage execute(ACLMessage msg) {
		ACLMessage response = agentDataStore.getActivityRequest().createReply();
		Event event = (Event) extractContent(msg);
		String eventId = event.getId();
		if (eventId.compareTo(Event.OPERATION_COMPLETED_SUCCESS) == 0) {
			response.setPerformative(ACLMessage.INFORM);
			ServiceCompleted serviceCompleted = new ServiceCompleted();
			// TODO : interactor must know service name
			serviceCompleted.setServiceName("");
			serviceCompleted.setDurationCompleted(1);
			fillContent(response, serviceCompleted);
		} else if (eventId.compareTo(Event.OPERATION_COMPLETED_FAILURE) == 0) {
			response.setPerformative(ACLMessage.FAILURE);
			ServiceFailed serviceFailed = new ServiceFailed();
			// TODO : interactor must know service name
			serviceFailed.setServiceName("");
			serviceFailed.setFailedReason("undefined");
			fillContent(response, serviceFailed);
		} else {
			response.setPerformative(ACLMessage.NOT_UNDERSTOOD);
			// TODO : to get here is impossible, throw NOT_UNDERSTOOD in Decision
		}
		agentDataStore.setActivityResult(response);
		return response;
	}
}
