package smartfactory.behaviours;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import smartfactory.dataStores.ProductDataStore;

public class TransitProductToNextStateBehaviour extends OneShotBehaviour implements ProductBehaviour {

	public TransitProductToNextStateBehaviour(Behaviour behaviour) {
		super(behaviour.getAgent());
		setDataStore(behaviour.getDataStore());
	}

	public static final int ProductIsInTheLastState = 0;
	public static final int ProductIsNotInTheLastState = 1;

	@Override
	public void action() {
		getProductDataStore().getProduct().moveToNextState();
		logger.info("associated product moved to next state");
	}

	@Override
	public int onEnd() {
		return getProductDataStore().getProduct().isInTheLastState() ? ProductIsInTheLastState : ProductIsNotInTheLastState;
	}

	@Override
	public ProductDataStore getProductDataStore() {
		return (ProductDataStore) getDataStore();
	}

	private static final long serialVersionUID = -4593835134099389482L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
