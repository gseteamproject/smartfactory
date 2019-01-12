package smartfactory.serviceProvisioning.interactors;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import smartfactory.interactors.AchieveREInitiatorInteractor;
import smartfactory.serviceProvisioning.ontology.ServiceCompleted;
import smartfactory.serviceProvisioning.ontology.ServiceFailed;
import smartfactory.serviceProvisioning.ontology.ServiceProposal;
import smartfactory.serviceProvisioning.ontology.ServiceRefusal;
import smartfactory.serviceProvisioning.ontology.ServiceRequest;
import smartfactory.serviceProvisioning.ontology.ServiceProvisioningOntology;
import smartfactory.utility.AgentDataStore;

public class ServiceProvisioningInitiator extends AchieveREInitiatorInteractor {

	public ServiceProvisioningInitiator(AgentDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public Vector<ACLMessage> prepareRequests(ACLMessage request) {

		Action action = prepareRequestContent();

		ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
		message.addReceiver(action.getActor());
		message.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
		message.setLanguage(FIPANames.ContentLanguage.FIPA_SL);
		message.setOntology(ServiceProvisioningOntology.ONTOLOGY_NAME);
		fillContent(message, action);

		Vector<ACLMessage> l = new Vector<ACLMessage>(1);
		l.addElement(message);
		return l;
	}

	private Action prepareRequestContent() {
		AID receiver = agentDataStore.getProcessOperation().agentDescription.getName();
		// content = operation name
		String serviceName = agentDataStore.getProcessOperation().serviceName;
		ServiceRequest serviceRequest = new ServiceRequest();
		serviceRequest.setServiceName(serviceName);

		Action action = new Action();
		action.setActor(receiver);
		action.setAction(serviceRequest);
		return action;
	}

	@Override
	public void handleAgree(ACLMessage agree) {
		ServiceProposal content = (ServiceProposal) extractContent(agree);
		logger.info("\"{}\" agent agreed, estimated duration {}",
				agentDataStore.getProcessOperation().agentDescription.getName().getLocalName(),
				content.getDurationEstimated());
	}

	@Override
	public void handleRefuse(ACLMessage refuse) {
		ServiceRefusal content = (ServiceRefusal) extractContent(refuse);
		logger.info("\"{}\" agent refused, cause {}",
				agentDataStore.getProcessOperation().agentDescription.getName().getLocalName(),
				content.getRefusalReason());
		agentDataStore.getProcessOperation().servicePerformedUnsuccesfully();
	}

	@Override
	public void handleInform(ACLMessage inform) {
		ServiceCompleted content = (ServiceCompleted) extractContent(inform);
		logger.info("\"{}\" agent successfully performed, completed duration {}",
				agentDataStore.getProcessOperation().agentDescription.getName().getLocalName(),
				content.getDurationCompleted());
		agentDataStore.getProcessOperation().servicePerformedSuccesfully();
	}

	@Override
	public void handleFailure(ACLMessage failure) {
		ServiceFailed content = (ServiceFailed) extractContent(failure);
		logger.info("\"{}\" agent failed to perform, cause {}",
				agentDataStore.getProcessOperation().agentDescription.getName().getLocalName(),
				content.getFailedReason());
		agentDataStore.getProcessOperation().servicePerformedUnsuccesfully();
	}

	@Override
	public int next() {
		return agentDataStore.getProcessOperation().isServicePerformedSuccesfully();
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
