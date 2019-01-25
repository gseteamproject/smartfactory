package procurementMarketBehaviours;

import basicClasses.Order;
import common.AgentDataStore;
import communication.MessageObject;
import interactors.ResponderBehaviour;
import jade.core.behaviours.OneShotBehaviour;

public class ReportFinancesBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = -6365251601845699295L;

	private String orderText;

	private AgentDataStore dataStore;

	private ResponderBehaviour interactionBehaviour;

	private MessageObject msgObj;

	public ReportFinancesBehaviour(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour.getAgent());
		this.interactionBehaviour = interactionBehaviour;
		this.dataStore = dataStore;
	}

	@Override
	public void action() {
		orderText = Order.gson.fromJson(dataStore.getRequestMessage().getContent(), Order.class).getTextOfOrder();

		msgObj = new MessageObject("AgentProcurementMarket", orderText + " is in finances");
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

		myAgent.addBehaviour(new ReportFinancesInitiatorBehaviour(interactionBehaviour, dataStore));
	}
}
