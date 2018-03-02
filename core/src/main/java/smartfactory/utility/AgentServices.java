package smartfactory.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jade.domain.FIPAAgentManagement.ServiceDescription;
import smartfactory.models.AgentService;

public class AgentServices {

	private HashMap<String, AgentService> agentServices = new HashMap<String, AgentService>();

	public void addService(AgentService agentService) {
		agentServices.put(agentService.name, agentService);
	}

	public List<ServiceDescription> getServiceDescriptions() {
		List<ServiceDescription> serviceDescriptions = new ArrayList<ServiceDescription>();
		for (AgentService agentService : agentServices.values()) {
			serviceDescriptions.add(agentService.getServiceDescription());
		}
		return serviceDescriptions;
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
}
