package smartfactory.behaviours;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import smartfactory.dataStores.ProductDataStore;

public abstract class ProductSubBehaviour extends OneShotBehaviour implements ProductBehaviour{

	public ProductSubBehaviour(Behaviour behaviour) {
		super(behaviour.getAgent());
		setDataStore(behaviour.getDataStore());
	}

	@Override
	public ProductDataStore getDataStore() {
		return (ProductDataStore) super.getDataStore();
	}

	private static final long serialVersionUID = 6289152061141729888L;
}
