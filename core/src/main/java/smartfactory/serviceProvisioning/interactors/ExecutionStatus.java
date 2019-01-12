package smartfactory.serviceProvisioning.interactors;

import jade.content.onto.basic.Action;
import jade.lang.acl.ACLMessage;
import smartfactory.interactors.Interactor;
import smartfactory.serviceProvisioning.ontology.ServiceRequest;
import smartfactory.utility.AgentDataStore;

public class ExecutionStatus extends Interactor {

	public ExecutionStatus(AgentDataStore dataStore) {
		super(dataStore);
	}

	public void execute(ACLMessage request) {
		Action a = (Action) extractContent(request);
		ServiceRequest serviceRequest = (ServiceRequest) a.getAction();
		String serviceName = serviceRequest.getServiceName();
		agentDataStore.getAgentServices().statusOfService(serviceName);
	}
}
