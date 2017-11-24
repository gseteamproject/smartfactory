package smartfactory.interactors.order;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import smartfactory.dataStores.OrderDataStore;
import smartfactory.interactors.AchieveREInitiatorInteractor;

public class ProductionProvisioningInitiator extends OrderInteractor implements AchieveREInitiatorInteractor {

	public ProductionProvisioningInitiator(OrderDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public Vector<ACLMessage> prepareRequests(ACLMessage request) {
		request = new ACLMessage(ACLMessage.REQUEST);
		request.addReceiver(dataStore.getProductionProvisioning().agentDescription.getName());
		request.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);

		Vector<ACLMessage> l = new Vector<ACLMessage>(1);
		l.addElement(request);
		return l;
	}

	@Override
	public void handleAgree(ACLMessage agree) {
		logger.info("\"{}\" agent agreed", dataStore.getProductionProvisioning().agentDescription.getName());
	}

	@Override
	public void handleRefuse(ACLMessage refuse) {
		logger.info("\"{}\" agent refused", dataStore.getProductionProvisioning().agentDescription.getName());
		dataStore.getProductionProvisioning().servicePerformedUnsuccesfully();
	}

	@Override
	public void handleInform(ACLMessage inform) {
		logger.info("\"{}\" agent successfully performed",
				dataStore.getProductionProvisioning().agentDescription.getName());
		dataStore.getProductionProvisioning().servicePerformedSuccesfully();
	}

	@Override
	public void handleFailure(ACLMessage failure) {
		logger.info("\"{}\" agent failed to perform", dataStore.getProductionProvisioning().agentDescription.getName());
		dataStore.getProductionProvisioning().servicePerformedUnsuccesfully();
	}

	@Override
	public int next() {
		return dataStore.getProductionProvisioning().isServicePerformedSuccesfully();
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
