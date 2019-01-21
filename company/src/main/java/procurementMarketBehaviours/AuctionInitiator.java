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
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class AuctionInitiator extends OneShotBehaviour {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final long serialVersionUID = -6100676860519799721L;
	private ACLMessage materialToBuy;
	private Order order;
	private String orderText;
	private ResponderBehaviour interactionBehaviour;
	private MessageObject msgObj;

	protected OrderDataStore dataStore;

	public AuctionInitiator(ResponderBehaviour interactionBehaviour, OrderDataStore dataStore) {
		super(interactionBehaviour.getAgent());
		this.interactionBehaviour = interactionBehaviour;
		this.materialToBuy = interactionBehaviour.getRequest();
		this.dataStore = dataStore;
	}

	public List<AID> findAgents(Agent a, String serviceName) {
		/* prepare service-search template */
		ServiceDescription requiredService = new ServiceDescription();
		requiredService.setName(serviceName);
		/*
		 * prepare agent-search template. agent-search template can have several
		 * service-search templates
		 */
		DFAgentDescription agentDescriptionTemplate = new DFAgentDescription();
		agentDescriptionTemplate.addServices(requiredService);

		List<AID> foundAgents = new ArrayList<AID>();
		try {
			/* perform request to DF-Agent */
			DFAgentDescription[] agentDescriptions = DFService.search(a, agentDescriptionTemplate);
			for (DFAgentDescription agentDescription : agentDescriptions) {
				/* store all found agents in an array for further processing */
				foundAgents.add(agentDescription.getName());
			}
		} catch (FIPAException exception) {
			logger.error("search failed", exception);
		}

		return foundAgents;
	}

	@Override
	public void action() {
		order = Order.gson.fromJson(materialToBuy.getContent(), Order.class);
		orderText = order.getTextOfOrder();
		dataStore.setPartsCount(order.orderList.size());

		msgObj = new MessageObject(materialToBuy, orderText);
		Communication.server.sendMessageToClient(msgObj);

		/*
		 * System.out.
		 * println("ProcurementAgent: Sending an info to ProcurementMarket to buy materials for "
		 * + orderText);
		 */
		dataStore.setBuyCount(0);
		for (OrderPart orderPart : order.orderList) {
			String requestedAction = "Order";
			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
			msg.setConversationId(requestedAction);
			msg.addReceiver(new AID(("AgentProcurementMarket"), AID.ISLOCALNAME));
			msg.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
			msg.setContent(orderPart.getTextOfOrderPart());

			msgObj = new MessageObject("AgentProcurementMarket",
					"looking for agents with procurement service " + orderPart.getGood().getClass().getSimpleName());
			Communication.server.sendMessageToClient(msgObj);

			/*
			 * System.out.println("\nlooking for agents with procurement service = " +
			 * orderPart.getPart().getClass().getSimpleName());
			 */
			List<AID> agents = findAgents(myAgent, orderPart.getGood().getClass().getSimpleName());
			if (!agents.isEmpty()) {

				msgObj = new MessageObject("AgentProcurementMarket",
						"agents providing service are found. Trying to get infromation...");
				Communication.server.sendMessageToClient(msgObj);
				/*
				 * System.out.
				 * println("agents providing service are found. trying to get infromation...");
				 */
				myAgent.addBehaviour(new RequestToBuy(agents, interactionBehaviour, orderPart, dataStore));
				// TODO: Check if material is really bought
			} else {
				msgObj = new MessageObject("AgentProcurementMarket", "No agents providing service are found.");
				Communication.server.sendMessageToClient(msgObj);

				/*
				 * System.out.println("no agents providing service are found");
				 */
			}
		}
	}
}
