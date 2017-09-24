package smartfactory.behaviours;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import smartfactory.dataStores.ProductDataStore;
import smartfactory.models.Order;

public class SelectAgentToPerformServiceBehaviour extends OneShotBehaviour implements ProductBehaviour {

	private DFAgentDescription agentProvidingService;

	public SelectAgentToPerformServiceBehaviour(Behaviour behaviour) {
		super(behaviour.getAgent());
		setDataStore(behaviour.getDataStore());
	}

	@Override
	public void action() {
		List<DFAgentDescription> agentsProvidingService = getProductDataStore().getOrder().agentsDescription;
		if (agentsProvidingService.size() > 0) {
			agentProvidingService = agentsProvidingService.get(0);
			logger.info("\"{}\" agent selected", agentProvidingService.getName());
		} else {
			agentProvidingService = null;
			logger.info("agent not selected");
		}
		getProductDataStore().getOrder().agentDescription = agentProvidingService;
	}

	@Override
	public int onEnd() {
		return agentProvidingService == null ? Order.AgentNotSelected : Order.AgentSelected;
	}

	@Override
	public ProductDataStore getProductDataStore() {
		return (ProductDataStore) getDataStore();
	}

	private static final long serialVersionUID = 4019502062871394333L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
