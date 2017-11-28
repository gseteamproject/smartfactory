package smartfactory.behaviours.production;

import jade.core.behaviours.TickerBehaviour;
import smartfactory.dataStores.ProductionDataStore;
import smartfactory.interactors.production.Status;

public class StatusBehaviour extends TickerBehaviour {

	Status interactor;

	public StatusBehaviour(ProductionProvisioningResponderBehaviour interactionBehaviour,
			ProductionDataStore dataStore) {
		super(interactionBehaviour.getAgent(), 500);
		this.interactor = new Status(dataStore);
	}

	@Override
	protected void onTick() {
		interactor.execute();
	}

	private static final long serialVersionUID = 8700210862668406877L;
}
