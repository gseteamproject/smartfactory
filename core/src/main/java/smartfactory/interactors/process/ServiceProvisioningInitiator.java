package smartfactory.interactors.process;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import smartfactory.dataStores.ProcessDataStore;
import smartfactory.interactors.AchieveREInitiatorInteractor;

public class ServiceProvisioningInitiator extends ProcessInteractor implements AchieveREInitiatorInteractor {

	public ServiceProvisioningInitiator(ProcessDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public Vector<ACLMessage> prepareRequests(ACLMessage request) {
		ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
		message.addReceiver(dataStore.getProcessOperation().agentDescription.getName());
		message.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
		// content = operation name
		message.setContent(dataStore.getProcessOperation().serviceName);

		Vector<ACLMessage> l = new Vector<ACLMessage>(1);
		l.addElement(message);
		return l;
	}

	@Override
	public void handleAgree(ACLMessage agree) {
		logger.info("\"{}\" agent agreed", dataStore.getProcessOperation().agentDescription.getName().getLocalName());
	}

	@Override
	public void handleRefuse(ACLMessage refuse) {
		logger.info("\"{}\" agent refused", dataStore.getProcessOperation().agentDescription.getName().getLocalName());
		dataStore.getProcessOperation().servicePerformedUnsuccesfully();
	}

	@Override
	public void handleInform(ACLMessage inform) {
		logger.info("\"{}\" agent successfully performed",
				dataStore.getProcessOperation().agentDescription.getName().getLocalName());
		dataStore.getProcessOperation().servicePerformedSuccesfully();
	}

	@Override
	public void handleFailure(ACLMessage failure) {
		logger.info("\"{}\" agent failed to perform",
				dataStore.getProcessOperation().agentDescription.getName().getLocalName());
		dataStore.getProcessOperation().servicePerformedUnsuccesfully();
	}

	@Override
	public int next() {
		return dataStore.getProcessOperation().isServicePerformedSuccesfully();
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
