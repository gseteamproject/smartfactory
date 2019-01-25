package procurementMarketBehaviours;

import java.util.List;
import java.util.Vector;

import basicClasses.CrossAgentData;
import basicClasses.OrderPart;
import common.AgentDataStore;
import communication.MessageObject;
import jade.core.AID;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.proto.ContractNetInitiator;

public class RequestToBuy extends ContractNetInitiator {

	private static final long serialVersionUID = 1531911633025115677L;

	private List<AID> procurementAgents;

	private OrderPart currentOrder;

	private int bestPrice = -1;

	private AgentDataStore dataStore;

	public RequestToBuy(List<AID> procurementAgents, OrderPart currentOrder, AgentDataStore dataStore) {
		super(null, null);
		this.procurementAgents = procurementAgents;
		this.currentOrder = currentOrder;
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
		MessageObject msgObj = new MessageObject("AgentProcurementMarket",
				currentOrder.getGood().getClass().getSimpleName() + " is found with " + bestPrice);
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

		for (int i = 0; i < currentOrder.getAmount(); i++) {
			CrossAgentData.materialStorage.add(currentOrder.getGood());
		}
	}
}
