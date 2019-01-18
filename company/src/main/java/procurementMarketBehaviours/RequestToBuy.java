package procurementMarketBehaviours;

import java.util.List;

import basicClasses.CrossAgentData;
import basicClasses.OrderPart;
import communication.Communication;
import communication.MessageObject;
import interactors.ResponderBehaviour;
import jade.core.AID;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/* possible states of request */
enum RequestState {
	PREPARE_CALL_FOR_PROPOSAL, HANDLE_CALL_FOR_PROPOSAL_REPLY, PREPARE_ACCEPT_PROPOSAL, HANDLE_ACCEPT_PROPOSAL_REPLY,
	DONE
};

public class RequestToBuy extends SimpleBehaviour {

	private static final long serialVersionUID = -1322936877118129497L;

	private ResponderBehaviour interactionBehaviour;
	private MessageObject msgObj;
	public static int buyCount;

	List<AID> procurementAgents;
	RequestState requestState;
	OrderPart currentOrder;

	public RequestToBuy(List<AID> procurementAgents, ResponderBehaviour interactionBehaviour,
			OrderPart currentOrder) {
		this.procurementAgents = procurementAgents;
		this.requestState = RequestState.PREPARE_CALL_FOR_PROPOSAL;
		this.currentOrder = currentOrder;
		this.interactionBehaviour = interactionBehaviour;
	}

	MessageTemplate replyTemplate = null;
	int repliesLeft = 0;

	AID bestPrinterAgent = null;
	int bestPrice = 0;

	@Override
	public void action() {
		/* perform actions accordingly to behaviour state */
		switch (requestState) {
		case PREPARE_CALL_FOR_PROPOSAL:
			prepareCallForProposal();
			break;

		case HANDLE_CALL_FOR_PROPOSAL_REPLY:
			handleCallForProposalReply();
			break;

		case PREPARE_ACCEPT_PROPOSAL:
			prepareAcceptProposal();
			break;

		case HANDLE_ACCEPT_PROPOSAL_REPLY:
			handleAcceptProposalReply();
			break;

		case DONE:
			break;

		default:
			break;
		}
	}

	private void handleAcceptProposalReply() {
		ACLMessage msg = myAgent.receive(replyTemplate);
		if (msg != null) {
			msgObj = new MessageObject("AgentProcurementMarket",
					currentOrder.getGood().getClass().getSimpleName() + " is found with " + bestPrice);
			Communication.server.sendMessageToClient(msgObj);

			/*
			 * System.out.println(String
			 * .format(currentOrder.getPart().getClass().getSimpleName() +
			 * " is found (price=%d)", bestPrice));
			 */

			repliesLeft = 0;
			requestState = RequestState.DONE;

			for (int i = 0; i < currentOrder.getAmount(); i++) {
				CrossAgentData.materialStorage.add(currentOrder.getGood());
			}
			buyCount += 1;
		} else {
			block();
		}
	}

	private void prepareAcceptProposal() {
		ACLMessage msg = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
		msg.addReceiver(bestPrinterAgent);
		msg.setConversationId("buying");
		msg.setContent("material");
		msg.setReplyWith(String.valueOf(System.currentTimeMillis()));

		replyTemplate = MessageTemplate.and(MessageTemplate.MatchConversationId("buying"),
				MessageTemplate.MatchInReplyTo(msg.getReplyWith()));
		repliesLeft = 1;

		myAgent.send(msg);

		requestState = RequestState.HANDLE_ACCEPT_PROPOSAL_REPLY;
	}

	private void handleCallForProposalReply() {
		ACLMessage msg = myAgent.receive(replyTemplate);
		if (msg != null) {
			int price = Integer.parseInt(msg.getContent());
			if (bestPrinterAgent == null || price < bestPrice) {
				bestPrinterAgent = msg.getSender();
				bestPrice = price;
			}
			repliesLeft--;
			if (repliesLeft == 0) {
				requestState = RequestState.PREPARE_ACCEPT_PROPOSAL;
			}
		} else {
			block();
		}
	}

	private void prepareCallForProposal() {
		ACLMessage msg = new ACLMessage(ACLMessage.CFP);
		for (AID agentProvidingService : procurementAgents) {
			msg.addReceiver(agentProvidingService);
		}
		msg.setConversationId("buying");
		msg.setContent("material");
		msg.setReplyWith(String.valueOf(System.currentTimeMillis()));

		replyTemplate = MessageTemplate.and(MessageTemplate.MatchConversationId("buying"),
				MessageTemplate.MatchInReplyTo(msg.getReplyWith()));
		repliesLeft = procurementAgents.size();
		myAgent.send(msg);

		requestState = RequestState.HANDLE_CALL_FOR_PROPOSAL_REPLY;
	}

	@Override
	public boolean done() {
		/* behaviour is finished when it reaches DONE state */
		if (buyCount == AuctionInitiator.partsCount) {
			interactionBehaviour.getRequestResult().execute(interactionBehaviour.getRequest());
		}

		return requestState == RequestState.DONE;
	}
}
