package smartfactory.behaviours.production;

import jade.core.behaviours.SimpleBehaviour;
import smartfactory.dataStores.ProductionDataStore;
import smartfactory.interactors.production.Work;

public class WorkBehaviour extends SimpleBehaviour {

	ProductionProvisioningResponderBehaviour interactionBehaviour;

	Work interactor;

	public WorkBehaviour(ProductionProvisioningResponderBehaviour interactionBehaviour, ProductionDataStore dataStore) {
		this.interactionBehaviour = interactionBehaviour;
		this.interactor = new Work(dataStore);
	}

	@Override
	public void action() {
		interactionBehaviour.setResult(interactor.execute(interactionBehaviour.getRequest()));
	}

	@Override
	public boolean done() {
		return interactor.done();
	}

	private static final long serialVersionUID = -6847064599739731732L;
}
