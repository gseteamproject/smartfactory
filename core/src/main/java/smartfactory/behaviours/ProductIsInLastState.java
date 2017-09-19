package smartfactory.behaviours;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import smartfactory.dataStores.ProductDataStore;

public class ProductIsInLastState extends OneShotBehaviour implements ProductBehaviour {

	public ProductIsInLastState(Behaviour behaviour) {
		super(behaviour.getAgent());
		setDataStore(behaviour.getDataStore());
	}

	@Override
	public void action() {
		logger.info("product is in last state");
	}

	@Override
	public ProductDataStore getProductDataStore() {
		return (ProductDataStore) getDataStore();
	}

	private static final long serialVersionUID = -6264847325552695878L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
