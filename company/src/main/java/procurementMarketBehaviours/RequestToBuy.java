package procurementMarketBehaviours;

import java.util.List;
import java.util.Vector;

import basicClasses.CrossAgentData;
import basicClasses.OrderPart;
import communication.Communication;
import communication.MessageObject;
import interactors.OrderDataStore;
import interactors.ResponderBehaviour;
import jade.core.AID;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.proto.ContractNetInitiator;

public class RequestToBuy extends ContractNetInitiator {

	private static final long serialVersionUID = 1531911633025115677L;

	private ResponderBehaviour interactionBehaviour;

	private MessageObject msgObj;

	private List<AID> procurementAgents;

	private OrderPart currentOrder;

	private OrderDataStore dataStore;

	private int bestPrice = -1;

	public RequestToBuy(List<AID> procurementAgents, ResponderBehaviour interactionBehaviour, OrderPart currentOrder,
			OrderDataStore dataStore) {
		super(null, null);
		this.procurementAgents = procurementAgents;
		this.currentOrder = currentOrder;
		this.interactionBehaviour = interactionBehaviour;
		this.dataStore = dataStore;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Vector prepareCfps(ACLMessage cfp) {
		ACLMessage msg = new ACLMessage(ACLMessage.CFP);
		for (AID procurementAgent : procurementAgents) {
			msg.addReceiver(procurementAgent);
		}
		msg.setContent("material");
		msg.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);

		Vector<ACLMessage> v = new Vector<ACLMessage>();
		v.add(msg);
		return v;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void handleAllResponses(Vector responses, Vector acceptances) {
		int messageId = -1;
		for (int i = 0; i < responses.size(); i++) {
			ACLMessage response = (ACLMessage) responses.get(i);
			if (response.getPerformative() == ACLMessage.PROPOSE) {
				int price = Integer.parseInt(response.getContent());
				if (messageId == -1 || price < bestPrice) {
					messageId = i;
					bestPrice = price;
				}
			}
		}
		for (int i = 0; i < responses.size(); i++) {
			ACLMessage accept = ((ACLMessage) responses.get(i)).createReply();
			accept.setContent("material");
			if (i == messageId) {
				accept.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
			} else {
				accept.setPerformative(ACLMessage.REJECT_PROPOSAL);
			}
			acceptances.add(accept);
		}
	}

	@Override
	protected void handleInform(ACLMessage inform) {
		msgObj = new MessageObject("AgentProcurementMarket",
				currentOrder.getGood().getClass().getSimpleName() + " is found with " + bestPrice);
		Communication.server.sendMessageToClient(msgObj);

		for (int i = 0; i < currentOrder.getAmount(); i++) {
			CrossAgentData.materialStorage.add(currentOrder.getGood());
		}
		int buyCount = dataStore.getBuyCount();
		buyCount += 1;
		dataStore.setBuyCount(buyCount);

		if (dataStore.getBuyCount() == dataStore.getPartsCount()) {
			interactionBehaviour.getRequestResult().execute(interactionBehaviour.getRequest());
		}
	}
}
