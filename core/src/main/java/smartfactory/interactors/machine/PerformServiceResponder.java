package smartfactory.interactors.machine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import smartfactory.dataStores.MachineDataStore;
import smartfactory.interactors.AchieveREResponderInteractor;

public class PerformServiceResponder extends MachineInteractor implements AchieveREResponderInteractor {

	public PerformServiceResponder(MachineDataStore dataStore) {
		super(dataStore);
	}

	@Override
	public ACLMessage handleRequest(ACLMessage request) throws NotUnderstoodException, RefuseException {
		// send AGREE
		ACLMessage agree = request.createReply();
		agree.setPerformative(ACLMessage.AGREE);
		logger.info("agreed to perform operation");
		return agree;
		// send REFUSE
		// throw new RefuseException("check-failed");
	}

	@Override
	public ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response) throws FailureException {
		// if agent AGREEd to request
		// send INFORM
		ACLMessage inform = request.createReply();
		inform.setPerformative(ACLMessage.INFORM);
		logger.info("operation is completed");
		return inform;
		// send FAILURE
		// throw new FailureException("unexpected-error");
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
