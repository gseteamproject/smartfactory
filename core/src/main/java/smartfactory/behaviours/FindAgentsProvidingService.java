package smartfactory.behaviours;

import java.util.ArrayList;
import java.util.List;

import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;

public class FindAgentsProvidingService extends ProductSubBehaviour {
	
	public FindAgentsProvidingService(Behaviour behaviour) {
		super(behaviour);
	}

	final public static int AgentsProvidingServiceFound = 0;

	@Override
	public void action() {
		DFAgentDescription agentDescriptionTemplate = new DFAgentDescription();
		agentDescriptionTemplate.addServices(dataStoreAccessor.getRequiredService());

		List<DFAgentDescription> agentsDescription = new ArrayList<DFAgentDescription>();
		try {
			DFAgentDescription[] agentDescriptions = DFService.search(myAgent, agentDescriptionTemplate);
			for (DFAgentDescription agentDescription : agentDescriptions) {
				agentsDescription.add(agentDescription);
			}
		} catch (FIPAException exception) {
			exception.printStackTrace();
		}

		dataStoreAccessor.setAgentsProvidingService(agentsDescription);
	}

	private static final long serialVersionUID = -6169428362127495247L;
}
