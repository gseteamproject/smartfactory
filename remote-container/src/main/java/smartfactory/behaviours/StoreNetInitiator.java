package smartfactory.behaviours;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.proto.ContractNetInitiator;

public class StoreNetInitiator extends ContractNetInitiator {

	List<AID> responders;

	public StoreNetInitiator(Agent a, List<AID> responders) {
		super(a, null);
		this.responders = responders;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected Vector prepareCfps(ACLMessage cfp) {
		System.out.println("preparing CFP");
		cfp = new ACLMessage(ACLMessage.CFP);
		cfp.setContent("block");
		cfp.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
		cfp.setReplyByDate(new Date(System.currentTimeMillis() + 1000));
		for (AID printerAgent : responders) {
			cfp.addReceiver(printerAgent);
		}
		Vector v = new Vector();
		v.add(cfp);
		return v;
	}

	@Override
	protected void handleInform(ACLMessage inform) {
		System.out.print("handle inform");
		super.handleInform(inform);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void handleAllResponses(Vector responses, Vector acceptances) {
		System.out.print("handle all responses");
		for (int i = 0; i < responses.size(); i++) {
			ACLMessage accept = ((ACLMessage) responses.get(i)).createReply();
			accept.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
			acceptances.add(accept);
		}
	}

	private static final long serialVersionUID = 8066035078263609809L;
}
