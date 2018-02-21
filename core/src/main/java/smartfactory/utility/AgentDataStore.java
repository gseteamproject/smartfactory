package smartfactory.utility;

import jade.core.behaviours.DataStore;
import jade.lang.acl.ACLMessage;
import smartfactory.configuration.AgentConfiguration;
import smartfactory.models.Process;
import smartfactory.models.ProcessOperation;
import smartfactory.models.Product;
import smartfactory.models.Resource;
import smartfactory.platform.AgentPlatform;

// TODO : rework mechanics of getters/setters so they log-error if there is null object of required type
public class AgentDataStore extends DataStore {

	public AgentPlatform getAgentPlatform() {
		return (AgentPlatform) get("agentPlatform");
	}

	public void setAgentPlatform(AgentPlatform agentPlatform) {
		put("agentPlatform", agentPlatform);
	}

	public EventSubscribers getEventSubsribers() {
		return (EventSubscribers) get("eventSubscribers");
	}

	public void setEventSubscribers(EventSubscribers eventSubscribers) {
		put("eventSubscribers", eventSubscribers);
	}

	public void setSubAgentConfiguration(AgentConfiguration agentConfiguration) {
		put("subAgentConfiguration", agentConfiguration);
	}

	public AgentConfiguration getSubAgentConfiguration() {
		return (AgentConfiguration) get("subAgentConfiguration");
	}

	public void setProcess(Process process) {
		put("process", process);
	}

	public Process getProcess() {
		return (Process) get("process");
	}

	public void setProcessOperation(ProcessOperation serviceProvisioning) {
		put("process-operation", serviceProvisioning);
	}

	public ProcessOperation getProcessOperation() {
		return (ProcessOperation) get("process-operation");
	}

	public void setProduct(Product product) {
		put("product", product);
	}

	public Product getProduct() {
		return (Product) get("product");
	}

	public void setResource(Resource machine) {
		put("resource", machine);
	}

	public Resource getResource() {
		// TODO : alert if there is no machine
		return (Resource) get("resource");
	}

	public ACLMessage getActivityResult() {
		return (ACLMessage) get("activity-result");
	}

	public void setActivityResult(ACLMessage result) {
		put("activity-result", result);
	}

	public void setActivityRequest(ACLMessage request) {
		put("activity-request", request);
	}

	public ACLMessage getActivityRequest() {
		return (ACLMessage) get("activity-request");
	}

	private static final long serialVersionUID = 4398092789071233362L;
}
