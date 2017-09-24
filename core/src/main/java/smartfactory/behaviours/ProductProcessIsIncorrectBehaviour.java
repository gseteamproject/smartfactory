package smartfactory.behaviours;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import smartfactory.dataStores.ProductDataStore;

public class ProductProcessIsIncorrectBehaviour extends OneShotBehaviour implements ProductBehaviour {

	public ProductProcessIsIncorrectBehaviour(Behaviour behaviour) {
		super(behaviour.getAgent());
		setDataStore(behaviour.getDataStore());
	}

	@Override
	public void action() {
		logger.info("product process is incorrect");
	}

	@Override
	public ProductDataStore getProductDataStore() {
		return (ProductDataStore) super.getDataStore();
	}

	private static final long serialVersionUID = 2423594959554488531L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
