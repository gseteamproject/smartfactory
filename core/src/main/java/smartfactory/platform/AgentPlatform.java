package smartfactory.platform;

import java.util.List;

import jade.content.ContentElement;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;
import smartfactory.configuration.AgentConfiguration;
import smartfactory.configuration.ContainerConfiguration;

public interface AgentPlatform {

	public List<DFAgentDescription> search(DFAgentDescription dfd);

	public void registerAgentServices(DFAgentDescription dfd);

	public void deregisterAgentServices();

	public void startContainer(ContainerConfiguration containerConfiguration);

	public void startAgent(AgentConfiguration agentConfiguration);

	public ContentElement extractContent(ACLMessage message);

	public void fillContent(ACLMessage message, ContentElement content);
}
