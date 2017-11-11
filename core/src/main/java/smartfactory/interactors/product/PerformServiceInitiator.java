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

	final static public int ServicePerformedSuccessfully = 0;
	final static public int ServicePerformedUnSuccessfully = 1;

	private boolean servicePerformedSuccessfully;

	@Override
	public Vector<ACLMessage> prepareRequests(ACLMessage request) {
		request = new ACLMessage(ACLMessage.REQUEST);
		request.addReceiver(dataStore.getOrder().agentDescription.getName());
		request.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);

		Vector<ACLMessage> l = new Vector<ACLMessage>(1);
		l.addElement(request);
		return l;
	}

	@Override
	public void handleAgree(ACLMessage agree) {
		logger.info("\"{}\" agent agreed", dataStore.getOrder().agentDescription.getName());
	}

	@Override
	public void handleRefuse(ACLMessage refuse) {
		logger.info("\"{}\" agent refused", dataStore.getOrder().agentDescription.getName());
		servicePerformedSuccessfully = false;
		dataStore.getOrder().removeSelectedAgentFromAgentsList();
	}

	@Override
	public void handleInform(ACLMessage inform) {
		logger.info("\"{}\" agent successfully performed", dataStore.getOrder().agentDescription.getName());
		servicePerformedSuccessfully = true;
	}

	@Override
	public void handleFailure(ACLMessage failure) {
		logger.info("\"{}\" agent failed to perform", dataStore.getOrder().agentDescription.getName());
		servicePerformedSuccessfully = false;
		dataStore.getOrder().removeSelectedAgentFromAgentsList();
	}

	@Override
	public int next() {
		// TODO : move decision to Order class
		return servicePerformedSuccessfully == true ? ServicePerformedSuccessfully : ServicePerformedUnSuccessfully;
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
