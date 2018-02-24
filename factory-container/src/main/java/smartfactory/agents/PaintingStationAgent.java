package smartfactory.agents;

import smartfactory.models.Resource;
import smartfactory.models.ResourceOperation;
import smartfactory.services.Services;
import jade.lang.acl.ACLMessage;
import smartfactory.behaviours.base.EventSubscriptionInitiatorBehaviour;
import smartfactory.behaviours.base.LaunchAgentBehaviour;
import smartfactory.configuration.AgentConfiguration;
import smartfactory.models.Event;
import smartfactory.models.EventHandler;
import smartfactory.models.PaintingStation;

public class PaintingStationAgent extends ResourceAgent {

	private static final long serialVersionUID = 5122917367234156798L;

	@Override
	public Resource createResource() {
		Resource resource = new PaintingStation();
		resource.addOperation(new PaintingOperation());
		return resource;
	}

	private class PaintingOperation extends ResourceOperation {

		public PaintingOperation() {
			super(Services.painting);
		}

		@Override
		public void execute() {
			executed = false;
			String processAgentName = PaintingProcessAgent.getUniqueName();

			AgentConfiguration subAgentConfiguration = new AgentConfiguration();
			subAgentConfiguration.name = processAgentName;
			subAgentConfiguration.className = PaintingProcessAgent.class.getName();
			agentDataStore.setSubAgentConfiguration(subAgentConfiguration);

			addBehaviour(new LaunchAgentBehaviour(agentDataStore));
			addBehaviour(
					new EventSubscriptionInitiatorBehaviour(processAgentName, "process-status", new EventHandler() {
						@Override
						public void callback(ACLMessage message) {
							String result = message.getContent();
							if (result.compareTo(Event.PROCESS_COMPLETED_SUCCESS) == 0) {
								completedSuccess();
							} else {
								completedFailure();
							}
						}
					}));
		}
	}
}
