package productionBehaviours;

import common.AgentDataStore;
import communication.MessageObject;
import interactors.ResponderBehaviour;
import jade.core.behaviours.OneShotBehaviour;

public class TakeFromStorageBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = 6717167573013445327L;

	private String orderText;

	private AgentDataStore dataStore;

	private ResponderBehaviour interactionBehaviour;

	private MessageObject msgObj;

	public TakeFromStorageBehaviour(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour.getAgent());
		this.dataStore = dataStore;
		this.interactionBehaviour = interactionBehaviour;
	}

	@Override
	public void action() {
		orderText = dataStore.getOrder().getTextOfOrder();

		msgObj = new MessageObject("AgentProduction",
				"Asking ProcurementAgent to take materials for " + orderText + " from materialStorage");
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

		myAgent.addBehaviour(new TakeFromStorageInitiatorBehaviour(interactionBehaviour, dataStore));
	}
}
