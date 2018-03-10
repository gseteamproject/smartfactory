package smartfactory.services;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import smartfactory.behaviours.base.EventSubscriptionInitiatorBehaviour;
import smartfactory.behaviours.base.LaunchAgentBehaviour;
import smartfactory.models.AgentService;
import smartfactory.models.Event;
import smartfactory.models.EventHandler;
import smartfactory.utility.AgentDataStore;

public class LaunchProcessService extends AgentService {

	public LaunchProcessService(String serviceName, AgentDataStore agentDataStore) {
		super(serviceName, agentDataStore);
	}

	@Override
	public void execute() {
		Agent agent = agentDataStore.getAgent();
		agent.addBehaviour(new LaunchAgentBehaviour(agentDataStore));
		agent.addBehaviour(new EventSubscriptionInitiatorBehaviour(
				agentDataStore.getSubAgentConfiguration().getAgentName(), "process-status", new EventHandler() {
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
