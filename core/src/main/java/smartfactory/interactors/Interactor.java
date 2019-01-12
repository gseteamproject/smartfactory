package smartfactory.interactors;

import jade.content.ContentElement;
import jade.lang.acl.ACLMessage;
import smartfactory.utility.AgentDataStore;

public class Interactor {

	protected AgentDataStore agentDataStore;

	public Interactor(AgentDataStore agentDataStore) {
		this.agentDataStore = agentDataStore;
	}

	public ContentElement extractContent(ACLMessage message) {
		return agentDataStore.getAgentPlatform().extractContent(message);
	}

	public void fillContent(ACLMessage message, ContentElement content) {
		agentDataStore.getAgentPlatform().fillContent(message, content);
	}
}
