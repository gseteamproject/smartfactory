package smartfactory.behaviours;

import java.util.Arrays;
import jade.core.behaviours.Behaviour;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import smartfactory.platform.AgentPlatform;
import smartfactory.platform.JADEPlatform;

public class FindAgentsProvidingService extends ProductSubBehaviour {

	private AgentPlatform jade;

	public FindAgentsProvidingService(Behaviour behaviour) {
		this(behaviour, new JADEPlatform());
	}

	public FindAgentsProvidingService(Behaviour behaviour, AgentPlatform jade) {
		super(behaviour);
		this.jade = jade;
	}

	final public static int AgentsProvidingServiceFound = 0;

	@Override
	public void action() {
		DFAgentDescription agentDescriptionTemplate = new DFAgentDescription();
		agentDescriptionTemplate.addServices(getDataStore().getRequiredService());

		try {
			getDataStore().setAgentsProvidingService(Arrays.asList(jade.search(myAgent, agentDescriptionTemplate)));
		} catch (FIPAException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public int onEnd() {
		return AgentsProvidingServiceFound;
	}

	private static final long serialVersionUID = -6169428362127495247L;
}
