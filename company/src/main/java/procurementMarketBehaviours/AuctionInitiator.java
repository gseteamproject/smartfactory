package procurementMarketBehaviours;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import basicClasses.Order;
import basicClasses.OrderPart;
import communication.Communication;
import communication.MessageObject;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class AuctionInitiator extends OneShotBehaviour {

	private static final long serialVersionUID = -6100676860519799721L;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ResponderBehaviour interactionBehaviour;

	protected OrderDataStore dataStore;

	public AuctionInitiator(ResponderBehaviour interactionBehaviour, OrderDataStore dataStore) {
		super(interactionBehaviour.getAgent());
		this.interactionBehaviour = interactionBehaviour;
		this.dataStore = dataStore;
	}

	public List<AID> findAgents(Agent a, String serviceName) {
		ServiceDescription requiredService = new ServiceDescription();
		requiredService.setName(serviceName);
		DFAgentDescription agentDescriptionTemplate = new DFAgentDescription();
		agentDescriptionTemplate.addServices(requiredService);

		List<AID> foundAgents = new ArrayList<AID>();
		try {
			DFAgentDescription[] agentDescriptions = DFService.search(a, agentDescriptionTemplate);
			for (DFAgentDescription agentDescription : agentDescriptions) {
				foundAgents.add(agentDescription.getName());
			}
		} catch (FIPAException exception) {
			logger.error("search failed", exception);
		}

		return foundAgents;
	}

	@Override
	public void action() {
		ACLMessage materialToBuy = interactionBehaviour.getRequest();
		Order order = Order.gson.fromJson(materialToBuy.getContent(), Order.class);
		String orderText = order.getTextOfOrder();

		MessageObject msgObj = new MessageObject(materialToBuy, orderText);
		Communication.server.sendMessageToClient(msgObj);

		ParallelBehaviour allRequestsToBuy = new ParallelBehaviour(myAgent, ParallelBehaviour.WHEN_ALL);
		for (OrderPart orderPart : order.orderList) {
			msgObj = new MessageObject("AgentProcurementMarket",
					"looking for agents with procurement service " + orderPart.getGood().getClass().getSimpleName());
			Communication.server.sendMessageToClient(msgObj);

			List<AID> agents = findAgents(myAgent, orderPart.getGood().getClass().getSimpleName());
			if (!agents.isEmpty()) {
				msgObj = new MessageObject("AgentProcurementMarket",
						"agents providing service are found. Trying to get infromation...");
				allRequestsToBuy.addSubBehaviour(new RequestToBuy(agents, orderPart));
				// TODO: Check if material is really bought
			} else {
				msgObj = new MessageObject("AgentProcurementMarket", "No agents providing service are found.");
			}
			Communication.server.sendMessageToClient(msgObj);
		}

		SequentialBehaviour sequence = new SequentialBehaviour(myAgent);
		sequence.addSubBehaviour(allRequestsToBuy);
		sequence.addSubBehaviour(new RequestsToBuyCompleted(interactionBehaviour));

		myAgent.addBehaviour(sequence);
	}
}
