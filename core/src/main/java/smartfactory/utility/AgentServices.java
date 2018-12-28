package smartfactory.utility;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.domain.FIPAAgentManagement.DFAgentDescription;
import smartfactory.models.AgentService;

public class AgentServices {

	private HashMap<String, AgentService> agentServices = new HashMap<String, AgentService>();

	private AgentDataStore agentDataStore;

	public AgentServices(AgentDataStore agentDataStore) {
		this.agentDataStore = agentDataStore;
	}

	public void addService(AgentService agentService) {
		agentServices.put(agentService.name, agentService);
	}

	public AgentService getService(String serviceName) {
		return agentServices.get(serviceName);
	}

	public void executeService(String serviceName) {
		getService(serviceName).execute();
	}

	public void terminateService(String serviceName) {
		getService(serviceName).terminate();
	}

	public void statusOfService(String serviceName) {
		getService(serviceName).status();
	}

	public boolean willExecuteService(String serviceName) {
		return getService(serviceName).willExecute();
	}

	public void deregisterAgentServices() {
		agentDataStore.getAgentPlatform().deregisterAgentServices();
	}

	public void registerAgentServices() {
		DFAgentDescription agentDescription = new DFAgentDescription();
		agentDescription.setName(agentDataStore.getAgent().getAID());
		for (AgentService agentService : agentServices.values()) {
			agentDescription.addServices(agentService.getServiceDescription());
			logger.info("providing \"{}\"", agentService.name);
		}

		agentDataStore.getAgentPlatform().registerAgentServices(agentDescription);
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
