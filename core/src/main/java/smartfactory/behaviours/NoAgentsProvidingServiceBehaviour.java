package smartfactory.behaviours;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import smartfactory.dataStores.ProductDataStore;

public class NoAgentsProvidingServiceBehaviour extends OneShotBehaviour implements ProductBehaviour {

	public NoAgentsProvidingServiceBehaviour(Behaviour behaviour) {
		super(behaviour.getAgent());
		setDataStore(behaviour.getDataStore());
	}

	@Override
	public void action() {
		logger.info("no agents providing service found");
	}

	@Override
	public ProductDataStore getProductDataStore() {
		return (ProductDataStore) getDataStore();
	}

	private static final long serialVersionUID = 8032514847832157952L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
