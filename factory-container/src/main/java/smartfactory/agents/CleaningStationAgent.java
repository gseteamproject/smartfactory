package smartfactory.agents;

import jade.lang.acl.ACLMessage;
import smartfactory.behaviours.base.EventSubscriptionInitiatorBehaviour;
import smartfactory.behaviours.base.LaunchAgentBehaviour;
import smartfactory.configuration.AgentConfiguration;
import smartfactory.models.CleaningStation;
import smartfactory.models.Event;
import smartfactory.models.EventHandler;
import smartfactory.models.Resource;
import smartfactory.models.ResourceOperation;
import smartfactory.services.Services;

public class CleaningStationAgent extends ResourceAgent {

	@Override
	public Resource createResource() {
		Resource resource = new CleaningStation();
		resource.addOperation(new CleaningOperation());
		return resource;
	}

	private class CleaningOperation extends ResourceOperation {

		public CleaningOperation() {
			super(Services.cleaning);
		}

		@Override
		public void execute() {
			executed = false;
			String processAgentName = CleaningProcessAgent.getUniqueName();

			AgentConfiguration subAgentConfiguration = new AgentConfiguration();
			subAgentConfiguration.name = processAgentName;
			subAgentConfiguration.className = CleaningProcessAgent.class.getName();
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

	private static final long serialVersionUID = 7802182814289213994L;
}
