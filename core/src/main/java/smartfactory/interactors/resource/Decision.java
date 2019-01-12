package smartfactory.interactors.resource;

import jade.content.onto.basic.Action;
import jade.lang.acl.ACLMessage;
import smartfactory.interactors.Interactor;
import smartfactory.ontology.ServiceProposal;
import smartfactory.ontology.ServiceRefusal;
import smartfactory.ontology.ServiceRequest;
import smartfactory.utility.AgentDataStore;

public class Decision extends Interactor {

	public Decision(AgentDataStore dataStore) {
		super(dataStore);
	}

	public ACLMessage execute(ACLMessage request) {
		Action a = (Action) extractContent(request);
		ServiceRequest serviceRequest = (ServiceRequest) a.getAction();
		String serviceName = serviceRequest.getServiceName();

		ACLMessage response = request.createReply();
		if (agentDataStore.getAgentServices().willExecuteService(serviceName)) {
			response.setPerformative(ACLMessage.AGREE);
			ServiceProposal serviceProposal = new ServiceProposal();
			serviceProposal.setServiceName(serviceName);
			serviceProposal.setDurationEstimated(0);
			fillContent(response, serviceProposal);
		} else {
			response.setPerformative(ACLMessage.REFUSE);
			ServiceRefusal serviceRefusal = new ServiceRefusal();
			serviceRefusal.setServiceName(serviceName);
			serviceRefusal.setRefusalReason("undefined");
			fillContent(response, serviceRefusal);
		}
		return response;
	}
}
