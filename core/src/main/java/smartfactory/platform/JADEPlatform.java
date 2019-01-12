package smartfactory.platform;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.content.ContentElement;
import jade.content.lang.Codec.CodecException;
import jade.content.onto.OntologyException;
import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;
import jade.util.ExtendedProperties;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;
import smartfactory.configuration.AgentConfiguration;
import smartfactory.configuration.ContainerConfiguration;

public class JADEPlatform implements AgentPlatform {

	private Agent agent;

	private AgentContainer container;

	public JADEPlatform() {
	}

	public JADEPlatform(Agent agent) {
		this.agent = agent;
	}

	@Override
	public List<DFAgentDescription> search(DFAgentDescription dfd) {
		try {
			return new ArrayList<DFAgentDescription>(Arrays.asList(DFService.search(agent, dfd)));
		} catch (FIPAException e) {
			logger.error("search failed", e);
		}
		return new ArrayList<DFAgentDescription>();
	}

	@Override
	public void registerAgentServices(DFAgentDescription dfd) {
		try {
			DFService.register(agent, dfd);
		} catch (FIPAException exception) {
			logger.error("register failed", exception);
		}
	}

	@Override
	public void deregisterAgentServices() {
		try {
			DFService.deregister(agent);
		} catch (FIPAException exception) {
			logger.error("deregister failed", exception);
		}
	}

	@Override
	public void startContainer(ContainerConfiguration configuration) {
		Runtime rt = Runtime.instance();
		rt.setCloseVM(true);

		ExtendedProperties props = new ExtendedProperties();
		props.setProperty(Profile.CONTAINER_NAME, configuration.getContainerName());
		props.setProperty(Profile.MAIN_HOST, configuration.getMainHost());
		props.setProperty(Profile.LOCAL_HOST, configuration.getLocalHost());
		props.setBooleanProperty(Profile.GUI, configuration.getGUI());

		ProfileImpl profile = new ProfileImpl(props);
		switch (configuration.getContainerType()) {
		case MainContainer:
			container = rt.createMainContainer(profile);
			break;
		default:
			container = rt.createAgentContainer(profile);
			break;
		}
	}

	@Override
	public void startAgent(AgentConfiguration configuration) {
		if (container == null) {
			container = agent.getContainerController();
		}
		try {
			container.createNewAgent(configuration.getAgentName(), configuration.getAgentClass(),
					configuration.getAgentParameters()).start();
		} catch (StaleProxyException e) {
			logger.error("createNewAgent failed", e);
		}
	}

	@Override
	public ContentElement extractContent(ACLMessage message) {
		try {
			return agent.getContentManager().extractContent(message);
		} catch (CodecException | OntologyException e) {
			logger.error("", e);
		}
		return null;
	}

	@Override
	public void fillContent(ACLMessage message, ContentElement content) {
		try {
			agent.getContentManager().fillContent(message, content);
		} catch (CodecException | OntologyException e) {
			logger.error("", e);
		}
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
