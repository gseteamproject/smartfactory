package salesMarketBehaviours;

import common.AgentDataStore;
import communication.MessageObject;
import interactors.ResponderBehaviour;
import jade.core.behaviours.OneShotBehaviour;

public class TakeFromWarehouseBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = 4233055394916376580L;

	private String orderText;

	private AgentDataStore dataStore;

	private ResponderBehaviour interactionBehaviour;

	private MessageObject msgObj;

	public TakeFromWarehouseBehaviour(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour.getAgent());
		this.dataStore = dataStore;
		this.interactionBehaviour = interactionBehaviour;
	}

	@Override
	public void action() {
		orderText = dataStore.getOrder().getTextOfOrder();

		msgObj = new MessageObject("AgentSalesMarket", orderText);
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);
		myAgent.addBehaviour(new TakeFromWarehouseInitiatorBehaviour(interactionBehaviour, dataStore));
	}
}
