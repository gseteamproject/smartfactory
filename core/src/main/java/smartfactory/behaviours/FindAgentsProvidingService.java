package smartfactory.behaviours;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.core.behaviours.Behaviour;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import smartfactory.platform.AgentPlatform;
import smartfactory.platform.JADEPlatform;

public class FindAgentsProvidingService extends ProductSubBehaviour {

	private AgentPlatform jade;

	private DFAgentDescription[] agentsProvidingService;

	public FindAgentsProvidingService(Behaviour behaviour) {
		this(behaviour, new JADEPlatform());
	}

	public FindAgentsProvidingService(Behaviour behaviour, AgentPlatform jade) {
		super(behaviour);
		this.jade = jade;
	}

	final public static int AgentsProvidingServiceFound = 0;
	final public static int AgentsProvidingServiceNotFound = 1;

	@Override
	public void action() {
		ServiceDescription serviceDescriptionTemplate = new ServiceDescription();
		serviceDescriptionTemplate.setName(getDataStore().getRequiredServiceName());

		DFAgentDescription agentDescriptionTemplate = new DFAgentDescription();
		agentDescriptionTemplate.addServices(serviceDescriptionTemplate);

		try {
			agentsProvidingService = jade.search(myAgent, agentDescriptionTemplate);
			getDataStore().setAgentsProvidingService(Arrays.asList(agentsProvidingService));
		} catch (FIPAException e) {
			logger.error("", e);
		}
	}

	@Override
	public int onEnd() {
		if (agentsProvidingService == null || agentsProvidingService.length < 1) {
			return AgentsProvidingServiceNotFound;
		}
		return AgentsProvidingServiceFound;
	}

	private static final long serialVersionUID = -6169428362127495247L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
