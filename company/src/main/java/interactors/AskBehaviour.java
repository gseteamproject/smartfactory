package interactors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.AgentDataStore;
import jade.core.behaviours.SimpleBehaviour;

public class AskBehaviour extends SimpleBehaviour {

	private static final long serialVersionUID = -4846116296001548998L;

	protected RequestResult interactor;

	protected ResponderBehaviour interactionBehaviour;

	protected AgentDataStore dataStore;

	private boolean isStarted;

	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	public AskBehaviour(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(null);
		this.interactionBehaviour = interactionBehaviour;
		this.interactor = interactionBehaviour.getRequestResult();
		this.dataStore = dataStore;
		this.isStarted = false;
	}

	@Override
	public void action() {
	}

	@Override
	public boolean done() {
		if (interactor.done()) {
			logger.info("Done of {}", interactionBehaviour.getAgent().getLocalName());
			interactionBehaviour.setResult(interactor.execute(interactionBehaviour.getRequest()));
			return true;
		}
		return false;
	}

	@Override
	public void reset() {
		super.reset();
		this.isStarted = false;
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
