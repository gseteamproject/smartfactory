package smartfactory.serviceProvisioning.interactors;

import jade.content.onto.basic.Action;
import jade.lang.acl.ACLMessage;
import smartfactory.interactors.Interactor;
import smartfactory.serviceProvisioning.ontology.ServiceRequest;
import smartfactory.utility.AgentDataStore;

public class ExecutionStart extends Interactor {

	public ExecutionStart(AgentDataStore dataStore) {
		super(dataStore);
	}

	// TODO : add generalization from OneShotInteractor
	public void execute(ACLMessage request) {
		agentDataStore.setActivityRequest(request);
		Action a = (Action) extractContent(request);
		ServiceRequest serviceRequest = (ServiceRequest) a.getAction();
		String serviceName = serviceRequest.getServiceName();
		agentDataStore.getAgentServices().executeService(serviceName);
	}
}
