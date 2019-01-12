package smartfactory.services;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import smartfactory.behaviours.base.SubscribeToEventInitiatorBehaviour;
import smartfactory.behaviours.base.SubscribeToEventInitiatorBehaviour.EventHandler;
import smartfactory.behaviours.base.LaunchAgentBehaviour;
import smartfactory.models.AgentService;
import smartfactory.models.Event;
import smartfactory.utility.AgentDataStore;

public class LaunchProcessService extends AgentService {

	public LaunchProcessService(String serviceName, AgentDataStore agentDataStore) {
		super(serviceName, agentDataStore);
	}

	@Override
	public void execute() {
		Agent agent = agentDataStore.getAgent();
		agent.addBehaviour(new LaunchAgentBehaviour(agentDataStore));
		agent.addBehaviour(new SubscribeToEventInitiatorBehaviour(
				agentDataStore.getSubAgentConfiguration().getAgentName(), "process-status", new EventHandler() {
					@Override
					public void callback(ACLMessage message) {
						Event event = (Event) agentDataStore.getAgentPlatform().extractContent(message);
						String eventId = event.getId();
						if (eventId.compareTo(Event.PROCESS_COMPLETED_SUCCESS) == 0) {
							completedSuccess();
						} else {
							completedFailure();
						}
					}
				}));
	}
}
