package sellingBehaviours;

import common.AgentDataStore;
import interactors.AchieveREInitiatorInteractorBehaviour;
import interactors.ResponderBehaviour;

public class AskFinancesInitiatorBehaviour extends AchieveREInitiatorInteractorBehaviour {

	private static final long serialVersionUID = 9100272572803215372L;

	public AskFinancesInitiatorBehaviour(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(new AskFinancesInitiator(interactionBehaviour, dataStore));
	}
}
