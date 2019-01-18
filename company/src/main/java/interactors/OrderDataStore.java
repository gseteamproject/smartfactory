package interactors;

import jade.core.Agent;
import jade.core.behaviours.DataStore;
import jade.lang.acl.ACLMessage;

public class OrderDataStore extends DataStore {

	private static final long serialVersionUID = 2340744686374901306L;

	public void setRequestMessage(ACLMessage msg) {
		put("request-message", msg);
	}

	public ACLMessage getRequestMessage() {
		return (ACLMessage) get("request-message");
	}

	public void setSubMessage(ACLMessage msg) {
		put("sub-message", msg);
	}

	public ACLMessage getSubMessage() {
		return (ACLMessage) get("sub-message");
	}

	public void setDeadline(long deadline) {
		put("deadline", deadline);
	}

	public long getDeadline() {
		return (long) get("deadline");
	}

	public void setAgentName(String agentName) {
		put("agentName", agentName);
	}

	public String getAgentName() {
		return (String) get("agentName");
	}

	public void setDeadlineResult(boolean isDeadline) {
		put("is-deadline", isDeadline);
	}

	public boolean getDeadlineResult() {
		return (boolean) get("is-deadline");
	}

	public void setThisAgent(Agent thisAgent) {
		put("thisAgent", thisAgent);
	}

	public Agent getThisAgent() {
		return (Agent) get("thisAgent");
	}
}
