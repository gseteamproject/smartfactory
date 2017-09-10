package smartfactory.behaviours;

import java.util.ArrayList;
import java.util.List;

import jade.core.Agent;
import jade.core.behaviours.DataStore;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class FindAgentsProvidingService extends OneShotBehaviour {
	
	public FindAgentsProvidingService(Agent agent, DataStore dataStore) {
		super(agent);
		setDataStore(dataStore);
	}

	@Override
	public void action() {
		DFAgentDescription agentDescriptionTemplate = new DFAgentDescription();
		agentDescriptionTemplate.addServices(getRequiredService());

		List<DFAgentDescription> agentsDescription = new ArrayList<DFAgentDescription>();
		try {
			DFAgentDescription[] agentDescriptions = DFService.search(myAgent, agentDescriptionTemplate);
			for (DFAgentDescription agentDescription : agentDescriptions) {
				agentsDescription.add(agentDescription);
			}
		} catch (FIPAException exception) {
			exception.printStackTrace();
		}

		setAgentsDescription(agentsDescription);
	}

	public ServiceDescription getRequiredService() {
		return (ServiceDescription) getDataStore().get("requiredService");
	}

	public void setAgentsDescription(List<DFAgentDescription> agentsDescription) {
		getDataStore().put("agentsDescription", agentsDescription);
	}

	private static final long serialVersionUID = -6169428362127495247L;
}
