package smartfactory.services;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import smartfactory.agents.ProcessAgent;
import smartfactory.behaviours.base.EventSubscriptionInitiatorBehaviour;
import smartfactory.behaviours.base.LaunchAgentBehaviour;
import smartfactory.configuration.AgentConfiguration;
import smartfactory.models.AgentService;
import smartfactory.models.Event;
import smartfactory.models.EventHandler;
import smartfactory.utility.AgentDataStore;

public class LaunchProcessService extends AgentService {

	Class<?> processClass;

	public LaunchProcessService(String serviceName, AgentDataStore agentDataStore, Class<?> processClass) {
		super(serviceName, agentDataStore);
		this.processClass = processClass;
	}

	@Override
	public void execute() {
		String agentName = ProcessAgent.getUniqueName();
		String agentClass = processClass.getName();
		AgentConfiguration agentConfiguration = new AgentConfiguration(agentName, agentClass);
		agentDataStore.setSubAgentConfiguration(agentConfiguration);

		Agent agent = agentDataStore.getAgent();
		agent.addBehaviour(new LaunchAgentBehaviour(agentDataStore));
		agent.addBehaviour(new EventSubscriptionInitiatorBehaviour(agentName, "process-status", new EventHandler() {
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
