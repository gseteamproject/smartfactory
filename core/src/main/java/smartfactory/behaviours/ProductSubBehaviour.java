package smartfactory.behaviours;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import smartfactory.accessors.ProductBehaviourDataAccessor;

public abstract class ProductSubBehaviour extends OneShotBehaviour {

	protected ProductBehaviourDataAccessor dataStoreAccessor;

	public ProductSubBehaviour(Behaviour behaviour) {
		super(behaviour.getAgent()); // TODO: myAgent variable affected by registerState call
		setDataStore(behaviour.getDataStore());
		dataStoreAccessor = new ProductBehaviourDataAccessor(getDataStore());
	}

	private static final long serialVersionUID = 6289152061141729888L;
}
