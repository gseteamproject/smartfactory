package smartfactory.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.domain.FIPAAgentManagement.ServiceDescription;
import smartfactory.eventSubscription.ontology.Event;
import smartfactory.utility.AgentDataStore;

//TODO : move to "services" package
public class AgentService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public String name;

	private ServiceDescription description;

	protected AgentDataStore agentDataStore;

	public AgentService(String serviceName, AgentDataStore agentDataStore) {
		this.name = serviceName;
		this.agentDataStore = agentDataStore;

		description = new ServiceDescription();
		description.setName(serviceName);
		description.setType("");
	}

	public ServiceDescription getServiceDescription() {
		return description;
	}

	public void execute() {
		logger.info("executing ...");
		synchronized (this) {
			// blocking function call
			agentDataStore.getResource().execute(name);
		}
		logger.info("completed");

		completedSuccess();
	}

	public void terminate() {
		logger.info("terminating ...");
		synchronized (this) {
			// blocking function call
			agentDataStore.getResource().terminate(name);
		}
		logger.info("terminated");

		completedFailure();
	}

	public void status() {
		logger.info("checking ...");
		synchronized (this) {
			// blocking function call
			agentDataStore.getResource().status(name);
		}
	}

	public boolean willExecute() {
		synchronized (this) {
			// blocking function call
			return agentDataStore.getResource().willExecute(name);
		}
	}

	protected void completedSuccess() {
		Event event = new Event();
		event.setId(Event.OPERATION_COMPLETED_SUCCESS);
		agentDataStore.getEventSubsribers().notifyAll(event);
	}

	protected void completedFailure() {
		Event event = new Event();
		event.setId(Event.OPERATION_COMPLETED_FAILURE);
		agentDataStore.getEventSubsribers().notifyAll(event);
	}
}
