package smartfactory.services;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import smartfactory.agents.CleaningProcessAgent;
import smartfactory.behaviours.base.EventSubscriptionInitiatorBehaviour;
import smartfactory.behaviours.base.LaunchAgentBehaviour;
import smartfactory.configuration.AgentConfiguration;
import smartfactory.models.AgentService;
import smartfactory.models.Event;
import smartfactory.models.EventHandler;
import smartfactory.utility.AgentDataStore;

public class CleaningService extends AgentService {

	public CleaningService(AgentDataStore agentDataStore) {
		super(Services.cleaning, agentDataStore);
	}

	@Override
	public void execute() {
		String processAgentName = CleaningProcessAgent.getUniqueName();

		AgentConfiguration subAgentConfiguration = new AgentConfiguration();
		subAgentConfiguration.name = processAgentName;
		subAgentConfiguration.className = CleaningProcessAgent.class.getName();
		agentDataStore.setSubAgentConfiguration(subAgentConfiguration);

		Agent agent = agentDataStore.getAgent();
		agent.addBehaviour(new LaunchAgentBehaviour(agentDataStore));
		agent.addBehaviour(
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
