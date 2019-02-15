package productionBehaviours;

import basicClasses.CrossAgentData;
import basicClasses.Order;
import common.AgentDataStore;
import interactors.AskBehaviour;
import interactors.ResponderBehaviour;

public class ProductionAskBehaviour extends AskBehaviour {

	private static final long serialVersionUID = -4443443755165652310L;

	public ProductionAskBehaviour(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour, dataStore);
	}

	@Override
	public void action() {
		if (!this.isStarted()) {
			Order orderToProduce = Order.fromJson(interactionBehaviour.getRequest().getContent());
			if (orderToProduce.searchInList(CrossAgentData.orderQueue) > -1) {
				CrossAgentData.orderQueue
						.get(orderToProduce.searchInList(CrossAgentData.orderQueue)).agent = interactionBehaviour
								.getAgent().getLocalName();
				myAgent.addBehaviour(new AskForMaterialsBehaviour(interactionBehaviour, dataStore));
			}
			this.setStarted(true);
		}
	}
}
