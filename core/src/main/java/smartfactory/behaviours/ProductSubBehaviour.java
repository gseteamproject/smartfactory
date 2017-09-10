package smartfactory.behaviours;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import smartfactory.accessors.ProductBehaviourDataStoreAccessor;

public abstract class ProductSubBehaviour extends OneShotBehaviour {

	protected ProductBehaviourDataStoreAccessor dataStoreAccessor;

	public ProductSubBehaviour(Behaviour behaviour) {
		super(behaviour.getAgent());
		setDataStore(behaviour.getDataStore());
		dataStoreAccessor = new ProductBehaviourDataStoreAccessor(getDataStore());
	}

	private static final long serialVersionUID = 6289152061141729888L;
}
