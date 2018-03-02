package smartfactory.agents;

import smartfactory.behaviours.resource.ServiceProvisioningResponderBehaviour;
import smartfactory.models.Resource;

public class ResourceAgent extends BaseAgent {

	private static final long serialVersionUID = -1254510527324190708L;

	public Resource createResource() {
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
