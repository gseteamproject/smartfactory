package smartfactory.behaviours;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.core.behaviours.Behaviour;
import jade.domain.FIPAAgentManagement.DFAgentDescription;

public class SelectAgentToPerformService extends ProductSubBehaviour {

	private DFAgentDescription agentProvidingService;

	public SelectAgentToPerformService(Behaviour behaviour) {
		super(behaviour);
	}

	final static public int AgentSelected = 0;
	final static public int AgentNotSelected = 1;

	@Override
	public void action() {
		List<DFAgentDescription> agentsProvidingService = getDataStore().getAgentsProvidingService();
		if (agentsProvidingService.size() > 0) {
			agentProvidingService = agentsProvidingService.get(0);
			logger.info("\"{}\" agent selected", agentProvidingService.getName());
		} else {
			agentProvidingService = null;
			logger.info("agent not selected");
		}
		getDataStore().setAgentProvidingService(agentProvidingService);
	}

	@Override
	public int onEnd() {
		return agentProvidingService == null ? AgentNotSelected : AgentSelected;
	}

	private static final long serialVersionUID = 4019502062871394333L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
