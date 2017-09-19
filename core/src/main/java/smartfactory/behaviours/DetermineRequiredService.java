package smartfactory.behaviours;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import smartfactory.dataStores.ProductDataStore;

public class DetermineRequiredService extends OneShotBehaviour implements ProductBehaviour {

	final static public int ServiceDetermined = 0;
	final static public int ServiceNotDetermined = 1;

	private String serviceName;

	public DetermineRequiredService(Behaviour behaviour) {
		super(behaviour.getAgent());
		setDataStore(behaviour.getDataStore());
	}

	@Override
	public void action() {
		serviceName = getProductDataStore().getProduct().getRequiredServiceName();
		getProductDataStore().setRequiredServiceName(serviceName);
		logger.info("required service \"{}\"", serviceName);
	}

	@Override
	public int onEnd() {
		if (StringUtils.isEmpty(serviceName)) {
			return ServiceNotDetermined;
		}
		return ServiceDetermined;
	}

	@Override
	public ProductDataStore getProductDataStore() {
		return (ProductDataStore) getDataStore();
	}

	private static final long serialVersionUID = -2422289734697182917L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
