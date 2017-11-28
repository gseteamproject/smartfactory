package smartfactory.behaviours.production;

import jade.core.behaviours.OneShotBehaviour;
import smartfactory.dataStores.ProductionDataStore;
import smartfactory.interactors.production.Decision;

public class DecisionBehaviour extends OneShotBehaviour {

	ProductionProvisioningResponderBehaviour interactionBehaviour;

	Decision interactor;

	public DecisionBehaviour(ProductionProvisioningResponderBehaviour interactionBehaviour,
			ProductionDataStore dataStore) {
		this.interactionBehaviour = interactionBehaviour;
		this.interactor = new Decision(dataStore);
	}

	@Override
	public void action() {
		interactionBehaviour.setResponse(interactor.execute(interactionBehaviour.getRequest()));
	}

	private static final long serialVersionUID = -3678721205803838963L;
}
