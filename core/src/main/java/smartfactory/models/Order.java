package smartfactory.models;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import jade.domain.FIPAAgentManagement.DFAgentDescription;

public class Order {

	final static public int ServiceDetermined = 0;
	final static public int ServiceNotDetermined = 1;

	public int isServiceDetermined() {
		if (StringUtils.isEmpty(serviceName)) {
			return ServiceNotDetermined;
		}
		return ServiceDetermined;
	}

	public Order() {
		serviceName = null;
	}

	public String serviceName;

	public List<DFAgentDescription> agentsDescription;

	final public static int AgentsFound = 0;
	final public static int AgentsNotFound = 1;

	public int isAgentsFound() {
		return agentsDescription.size() > 0 ? AgentsFound : AgentsNotFound;
	}

	public DFAgentDescription agentDescription;
}
