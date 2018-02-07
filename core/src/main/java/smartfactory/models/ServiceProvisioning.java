package smartfactory.models;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import jade.domain.FIPAAgentManagement.DFAgentDescription;

public class ServiceProvisioning {

	public ServiceProvisioning() {
		serviceName = null;
		agentDescription = null;
	}

	public String serviceName;

	final static public int ServiceDetermined = 0;
	final static public int ServiceNotDetermined = 1;

	public int isServiceDetermined() {
		if (StringUtils.isEmpty(serviceName)) {
			return ServiceNotDetermined;
		}
		return ServiceDetermined;
	}

	public List<DFAgentDescription> agentsDescription;

	final public static int AgentsFound = 0;
	final public static int AgentsNotFound = 1;

	public int isAgentsFound() {
		return agentsDescription.isEmpty() ? AgentsNotFound : AgentsFound;
	}

	// TODO deal with null pointer
	public DFAgentDescription agentDescription;

	final static public int AgentSelected = 0;
	final static public int AgentNotSelected = 1;

	public int isAgentSelected() {
		// TODO another way to check if agent is selected
		return agentDescription == null ? AgentNotSelected : AgentSelected;
	}

	private boolean servicePerformedSuccessfully;

	public void servicePerformedUnsuccesfully() {
		servicePerformedSuccessfully = false;
		agentsDescription.remove(agentDescription);
	}

	public void servicePerformedSuccesfully() {
		servicePerformedSuccessfully = true;
	}

	final static public int ServicePerformedSuccessfully = 0;
	final static public int ServicePerformedUnSuccessfully = 1;

	public int isServicePerformedSuccesfully() {
		return servicePerformedSuccessfully == true ? ServicePerformedSuccessfully : ServicePerformedUnSuccessfully;
	}
}
