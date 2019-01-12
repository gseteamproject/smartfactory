package smartfactory.interactors.resource;

import jade.content.onto.basic.Action;
import jade.lang.acl.ACLMessage;
import smartfactory.interactors.Interactor;
import smartfactory.ontology.ServiceRequest;
import smartfactory.utility.AgentDataStore;

public class ExecutionStart extends Interactor {

	public ExecutionStart(AgentDataStore dataStore) {
		super(dataStore);
	}

	public void execute(ACLMessage request) {
		agentDataStore.setActivityRequest(request);
		Action a = (Action) extractContent(request);
		ServiceRequest serviceRequest = (ServiceRequest) a.getAction();
		String serviceName = serviceRequest.getServiceName();
		agentDataStore.getAgentServices().executeService(serviceName);
	}
}
