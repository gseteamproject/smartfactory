package procurementMarketBehaviours;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import basicClasses.Order;
import common.AgentDataStore;
import communication.MessageObject;
import interactors.AchieveREInitiatorInteractor;
import interactors.ResponderBehaviour;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class ReportFinancesInitiator extends AchieveREInitiatorInteractor {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ResponderBehaviour interactionBehaviour;

	private MessageObject msgObj;

	public ReportFinancesInitiator(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(dataStore);
		this.interactionBehaviour = interactionBehaviour;
	}

	@Override
	public Vector<ACLMessage> prepareRequests(ACLMessage request) {
		ACLMessage message = new ACLMessage(ACLMessage.REQUEST);

		String requestedAction = "Materials";
		message.addReceiver(new AID(("AgentFinances"), AID.ISLOCALNAME));
		logger.info("{}", dataStore.getRequestMessage().getContent());
		setup(message, requestedAction, false);

		return l;
	}

	@Override
	public void handleInform(ACLMessage inform) {

		handleResponse(inform);

		Order order = Order.gson.fromJson(inform.getContent(), Order.class);
		orderText = order.getTextOfOrder();

		msgObj = new MessageObject("AgentProcurementMarket", orderText + " is allowed to purchase");
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

		ACLMessage msgToPurchase = (ACLMessage) inform.clone();
		dataStore.setSubMessage(msgToPurchase);

		// add order to queue
		// Selling.procurementQueue.add(order);

		interactionBehaviour.getAgent().addBehaviour(new AuctionInitiator(interactionBehaviour, dataStore));
	}

	@Override
	public void handleFailure(ACLMessage failure) {
		handleResponse(failure);

		orderText = Order.gson.fromJson(failure.getContent(), Order.class).getTextOfOrder();

		msgObj = new MessageObject("AgentProcurementMarket", orderText + " is forbidden to purchase");
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);
	}

	@Override
	public void handleAgree(ACLMessage agree) {
		handleResponse(agree);

		orderText = Order.gson.fromJson(agree.getContent(), Order.class).getTextOfOrder();

		msgObj = new MessageObject("AgentProcurementMarket", "Purchase of " + orderText + " is initiated.");
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);
	}

	@Override
	public void handleRefuse(ACLMessage refuse) {
	}

	@Override
	public int next() {
		return 0;
	}
}
