package smartfactory.behaviours.production;

import jade.core.behaviours.WakerBehaviour;
import smartfactory.dataStores.ProductionDataStore;
import smartfactory.interactors.production.Deadline;
import smartfactory.models.Production;

public class DeadlineBehaviour extends WakerBehaviour {

	ProductionProvisioningResponderBehaviour interactionBehaviour;

	Deadline interactor;

	public DeadlineBehaviour(ProductionProvisioningResponderBehaviour interactionBehaviour,
			ProductionDataStore dataStore) {
		super(interactionBehaviour.getAgent(), Production.DURATION_LIMIT * 1000);
		this.interactionBehaviour = interactionBehaviour;
		this.interactor = new Deadline(dataStore);
	}

	@Override
	protected void onWake() {
		interactionBehaviour.setResult(interactor.execute(interactionBehaviour.getRequest()));
	}

	private static final long serialVersionUID = -5146040663591800240L;
}
