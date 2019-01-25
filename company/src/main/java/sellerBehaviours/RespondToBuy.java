package sellerBehaviours;

import java.util.Random;

import common.AgentDataStore;
import communication.MessageObject;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.proto.ContractNetResponder;

public class RespondToBuy extends ContractNetResponder {

	private static final long serialVersionUID = -6600650210336116167L;

	private AgentDataStore dataStore;

	public RespondToBuy(Agent agent, AgentDataStore dataStore) {
		super(agent, createMessageTemplate(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET));
		this.dataStore = dataStore;
	}

	@Override
	protected ACLMessage handleCfp(ACLMessage cfp) throws RefuseException, FailureException, NotUnderstoodException {
		int price = new Random().nextInt(100);

		MessageObject msgObj = new MessageObject("AgentProcurementMarket",
				dataStore.getGoodName() + " price is " + price);
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

		ACLMessage reply = cfp.createReply();
		reply.setPerformative(ACLMessage.PROPOSE);
		reply.setContent(String.valueOf(price));
		return reply;
	}

	@Override
	protected ACLMessage handleAcceptProposal(ACLMessage cfp, ACLMessage propose, ACLMessage accept)
			throws FailureException {
		MessageObject msgObj = new MessageObject("AgentProcurementMarket",
				"Delivering " + dataStore.getGoodName() + "(s).");
		dataStore.getAgentPlatform().sendMessageToWebClient(msgObj);

		ACLMessage reply = accept.createReply();
		reply.setPerformative(ACLMessage.INFORM);
		return reply;
	}
}
