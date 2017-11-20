package smartfactory.interactors.product;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import smartfactory.dataStores.ProductDataStore;
import smartfactory.interactors.AchieveREInitiatorInteractor;

public class PerformServiceInitiator extends ProductInteractor implements AchieveREInitiatorInteractor {

	public PerformServiceInitiator(ProductDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public Vector<ACLMessage> prepareRequests(ACLMessage request) {
		request = new ACLMessage(ACLMessage.REQUEST);
		request.addReceiver(dataStore.getServiceProvisioning().agentDescription.getName());
		request.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);

		Vector<ACLMessage> l = new Vector<ACLMessage>(1);
		l.addElement(request);
		return l;
	}

	@Override
	public void handleAgree(ACLMessage agree) {
		logger.info("\"{}\" agent agreed", dataStore.getServiceProvisioning().agentDescription.getName());
	}

	@Override
	public void handleRefuse(ACLMessage refuse) {
		logger.info("\"{}\" agent refused", dataStore.getServiceProvisioning().agentDescription.getName());
		dataStore.getServiceProvisioning().servicePerformedUnsuccesfully();
	}

	@Override
	public void handleInform(ACLMessage inform) {
		logger.info("\"{}\" agent successfully performed", dataStore.getServiceProvisioning().agentDescription.getName());
		dataStore.getServiceProvisioning().servicePerformedSuccesfully();
	}

	@Override
	public void handleFailure(ACLMessage failure) {
		logger.info("\"{}\" agent failed to perform", dataStore.getServiceProvisioning().agentDescription.getName());
		dataStore.getServiceProvisioning().servicePerformedUnsuccesfully();
	}

	@Override
	public int next() {
		return dataStore.getServiceProvisioning().isServicePerformedSuccesfully();
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
