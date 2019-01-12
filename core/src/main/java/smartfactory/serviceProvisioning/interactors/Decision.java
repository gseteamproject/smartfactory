package smartfactory.serviceProvisioning.interactors;

import jade.content.onto.basic.Action;
import jade.lang.acl.ACLMessage;
import smartfactory.interactors.Interactor;
import smartfactory.serviceProvisioning.ontology.ServiceProposal;
import smartfactory.serviceProvisioning.ontology.ServiceRefusal;
import smartfactory.serviceProvisioning.ontology.ServiceRequest;
import smartfactory.utility.AgentDataStore;

public class Decision extends Interactor {

	public Decision(AgentDataStore dataStore) {
		super(dataStore);
	}

	// TODO : add generalization from OneShotInteractor
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
