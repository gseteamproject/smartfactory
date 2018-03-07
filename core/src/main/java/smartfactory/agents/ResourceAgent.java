package smartfactory.agents;

import smartfactory.behaviours.resource.ServiceProvisioningResponderBehaviour;
import smartfactory.models.Resource;

public class ResourceAgent extends BaseAgent {

	private static final long serialVersionUID = -1254510527324190708L;

	protected Resource createResource() {
		switch (getAgentConfiguration().getResourceType()) {
		case physical:
			return createPhysicalResource();
		default:
			return new Resource();
		}
	}

	public Resource createPhysicalResource() {
		return new Resource();
	}

	@Override
	protected void setupData() {
		super.setupData();
		agentDataStore.setResource(createResource());
	}

	@Override
	protected void setupBehaviours() {
		super.setupBehaviours();
		addBehaviour(new ServiceProvisioningResponderBehaviour(agentDataStore));
	}
}
