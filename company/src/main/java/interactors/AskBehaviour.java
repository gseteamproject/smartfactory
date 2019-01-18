package interactors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.core.behaviours.SimpleBehaviour;

public class AskBehaviour extends SimpleBehaviour {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final long serialVersionUID = -4846116296001548998L;
	protected RequestResult interactor;
	protected ResponderBehaviour interactionBehaviour;
	protected OrderDataStore dataStore;
	protected boolean isStarted;

	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	public AskBehaviour(ResponderBehaviour interactionBehaviour, OrderDataStore dataStore) {
		super(interactionBehaviour.getAgent());
		this.interactionBehaviour = interactionBehaviour;
		this.interactor = interactionBehaviour.getRequestResult();
		this.dataStore = dataStore;
		this.setStarted(true);
	}

	@Override
	public void action() {
	}

	@Override
	public boolean done() {
		if (interactor.done()) {
			logger.info("Done of {}", interactionBehaviour.getAgent().getLocalName());
			interactionBehaviour.setResult(interactor.execute(interactionBehaviour.getRequest()));
		}
		return interactor.done();
	}
}
