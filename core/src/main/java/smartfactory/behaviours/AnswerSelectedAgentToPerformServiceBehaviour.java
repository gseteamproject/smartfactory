package smartfactory.behaviours;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.core.Agent;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREResponder;

public class AnswerSelectedAgentToPerformServiceBehaviour extends AchieveREResponder {

	public AnswerSelectedAgentToPerformServiceBehaviour(Agent a) {
		super(a, AchieveREResponder.createMessageTemplate(FIPANames.InteractionProtocol.FIPA_REQUEST));
	}

	@Override
	protected ACLMessage prepareResponse(ACLMessage request) throws NotUnderstoodException, RefuseException {
		// send AGREE
		ACLMessage agree = request.createReply();
		agree.setPerformative(ACLMessage.AGREE);
		logger.info("agreed to perform operation");
		return agree;
		// send REFUSE
		// throw new RefuseException("check-failed");
	}

	@Override
	protected ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response) throws FailureException {
		// if agent AGREEd to request
		// send INFORM
		ACLMessage inform = request.createReply();
		inform.setPerformative(ACLMessage.INFORM);
		logger.info("operation is completed");
		return inform;
		// send FAILURE
		// throw new FailureException("unexpected-error");
	}

	private static final long serialVersionUID = 7220989635322510713L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
