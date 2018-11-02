package smartfactory.interactors.process;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import smartfactory.interactors.AchieveREInitiatorInteractor;
import smartfactory.utility.AgentDataStore;

public class ServiceProvisioningInitiator extends AchieveREInitiatorInteractor {

	public ServiceProvisioningInitiator(AgentDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public Vector<ACLMessage> prepareRequests(ACLMessage request) {
		ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
		message.addReceiver(agentDataStore.getProcessOperation().agentDescription.getName());
		message.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
		// content = operation name
		message.setContent(agentDataStore.getProcessOperation().serviceName);

		Vector<ACLMessage> l = new Vector<ACLMessage>(1);
		l.addElement(message);
		return l;
	}

	@Override
	public void handleAgree(ACLMessage agree) {
		logger.info("\"{}\" agent agreed",
				agentDataStore.getProcessOperation().agentDescription.getName().getLocalName());
	}

	@Override
	public void handleRefuse(ACLMessage refuse) {
		logger.info("\"{}\" agent refused",
				agentDataStore.getProcessOperation().agentDescription.getName().getLocalName());
		agentDataStore.getProcessOperation().servicePerformedUnsuccesfully();
	}

	@Override
	public void handleInform(ACLMessage inform) {
		logger.info("\"{}\" agent successfully performed",
				agentDataStore.getProcessOperation().agentDescription.getName().getLocalName());
		agentDataStore.getProcessOperation().servicePerformedSuccesfully();
	}

	@Override
	public void handleFailure(ACLMessage failure) {
		logger.info("\"{}\" agent failed to perform",
				agentDataStore.getProcessOperation().agentDescription.getName().getLocalName());
		agentDataStore.getProcessOperation().servicePerformedUnsuccesfully();
	}

	@Override
	public int next() {
		return agentDataStore.getProcessOperation().isServicePerformedSuccesfully();
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
