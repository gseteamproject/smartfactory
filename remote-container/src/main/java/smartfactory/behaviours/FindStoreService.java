package smartfactory.behaviours;

import java.util.ArrayList;
import java.util.List;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class FindStoreService extends Behaviour {

	public FindStoreService(Agent a) {
		super(a);
	}

	boolean isDone = false;

	@Override
	public void action() {
		List<AID> agents = findAgents("store");
		if (!agents.isEmpty()) {
			myAgent.addBehaviour(new StoreNetInitiator(myAgent, agents));
			isDone = true;
		}
	}

	@Override
	public boolean done() {
		return isDone;
	}

	private List<AID> findAgents(String serviceName) {
		ServiceDescription requiredService = new ServiceDescription();
		requiredService.setName(serviceName);

		DFAgentDescription agentDescriptionTemplate = new DFAgentDescription();
		agentDescriptionTemplate.addServices(requiredService);

		List<AID> foundAgents = new ArrayList<AID>();
		try {
			DFAgentDescription[] agentDescriptions = DFService.search(myAgent, agentDescriptionTemplate);
			for (DFAgentDescription agentDescription : agentDescriptions) {
				foundAgents.add(agentDescription.getName());
			}
		} catch (FIPAException exception) {
			exception.printStackTrace();
		}

		return foundAgents;
	}

	private static final long serialVersionUID = -7138439645391982551L;
}
