package smartfactory.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.core.Agent;
import jade.core.behaviours.DataStore;
import jade.lang.acl.ACLMessage;
import smartfactory.configuration.AgentConfiguration;
import smartfactory.eventSubscription.EventSubscribers;
import smartfactory.models.Process;
import smartfactory.models.ProcessOperation;
import smartfactory.models.Product;
import smartfactory.models.Resource;
import smartfactory.platform.AgentPlatform;
import smartfactory.platform.JADEPlatform;

public class AgentDataStore extends DataStore {

	private static final long serialVersionUID = 4398092789071233362L;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public AgentDataStore(Agent agent) {
		super();
		setAgent(agent);
		setAgentPlatform(new JADEPlatform(agent));
		setAgentServices(new AgentServices(this));
		setEventSubscribers(new EventSubscribers(this));
	}

	@Override
	public Object get(Object key) {
		Object result = super.get(key);
		if (result == null) {
			logger.error("no object for key={}", key);
		}
		return result;
	}

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

	public void setAgentServices(AgentServices agentServices) {
		put("agentServices", agentServices);
	}

	public AgentServices getAgentServices() {
		return (AgentServices) get("agentServices");
	}

	public Agent getAgent() {
		return (Agent) get("agent");
	}

	public void setAgent(Agent agent) {
		put("agent", agent);
	}
}
