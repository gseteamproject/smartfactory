package smartfactory.models;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import jade.domain.FIPAAgentManagement.DFAgentDescription;

public class ProductionProvisioning {

	public String productionName;

	public static final int ProductionDetermined = 0;
	public static final int ProductionNotDetermined = 1;

	public int isProductionDetermined() {
		if (StringUtils.isEmpty(productionName)) {
			return ProductionNotDetermined;
		}
		return ProductionDetermined;
	}

	public List<DFAgentDescription> agentsDescription;

	public static final int AgentsFound = 0;
	public static final int AgentsNotFound = 1;

	public int isAgentsFound() {
		return agentsDescription.size() > 0 ? AgentsFound : AgentsNotFound;
	}

	// TODO deal with null pointer
	public DFAgentDescription agentDescription;

	public static final int AgentSelected = 0;
	public static final int AgentNotSelected = 1;

	public int isAgentSelected() {
		// TODO another way to check if agent is selected
		return agentDescription == null ? AgentNotSelected : AgentSelected;
	}

	private boolean servicePerformedSuccessfully;

	public void servicePerformedUnsuccesfully() {
		servicePerformedSuccessfully = false;
		agentsDescription.remove(agentDescription);
	}

	public void servicePerformedSuccesfully() {
		servicePerformedSuccessfully = true;
	}

	public static final int ProductionPerformedSuccessfully = 0;
	public static final int ProductionPerformedUnSuccessfully = 1;

	public int isServicePerformedSuccesfully() {
		return servicePerformedSuccessfully == true ? ProductionPerformedSuccessfully
				: ProductionPerformedUnSuccessfully;
	}

}
