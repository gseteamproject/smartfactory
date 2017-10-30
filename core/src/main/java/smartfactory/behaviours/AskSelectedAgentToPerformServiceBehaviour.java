package smartfactory.behaviours;

import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.core.behaviours.Behaviour;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;
import smartfactory.dataStores.ProductDataStore;

public class AskSelectedAgentToPerformServiceBehaviour extends AchieveREInitiator {

	final static public int ServicePerformedSuccessfully = 0;
	final static public int ServicePerformedUnSuccessfully = 1;

	private boolean servicePerformedSuccessfully;

	public AskSelectedAgentToPerformServiceBehaviour(Behaviour behaviour) {
		super(behaviour.getAgent(), null, behaviour.getDataStore());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected Vector prepareRequests(ACLMessage request) {
		request = new ACLMessage(ACLMessage.REQUEST);
		request.addReceiver(getProductDataStore().getOrder().agentDescription.getName());
		request.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);

		Vector l = new Vector(1);
		l.addElement(request);
		return l;
	}

	@Override
	protected void handleAgree(ACLMessage agree) {
		logger.info("\"{}\" agent agreed", getProductDataStore().getOrder().agentDescription.getName());
	}

	@Override
	protected void handleRefuse(ACLMessage refuse) {
		logger.info("\"{}\" agent refused", getProductDataStore().getOrder().agentDescription.getName());
		servicePerformedSuccessfully = false;
		removeAgentProvidingService();
	}

	@Override
	protected void handleInform(ACLMessage inform) {
		logger.info("\"{}\" agent successfully performed", getProductDataStore().getOrder().agentDescription.getName());
		servicePerformedSuccessfully = true;
	}

	@Override
	protected void handleFailure(ACLMessage failure) {
		logger.info("\"{}\" agent failed to perform", getProductDataStore().getOrder().agentDescription.getName());
		servicePerformedSuccessfully = false;
		removeAgentProvidingService();
	}

	public void removeAgentProvidingService() {
		DFAgentDescription agentProvidingService = getProductDataStore().getOrder().agentDescription;
		List<DFAgentDescription> agentsProvidingService = getProductDataStore().getOrder().agentsDescription;
		agentsProvidingService.remove(agentProvidingService);
	}

	@Override
	public int onEnd() {
		return servicePerformedSuccessfully == true ? ServicePerformedSuccessfully : ServicePerformedUnSuccessfully;
	}

	public ProductDataStore getProductDataStore() {
		return (ProductDataStore) super.getDataStore();
	}

	private static final long serialVersionUID = 1797435133895300883L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
