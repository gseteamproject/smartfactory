package smartfactory.agents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.core.Agent;

public class BaseAgent extends Agent {

	@Override
	final protected void setup() {
		initializeData();
		initializeGUI();
		initializeBehaviours();
		initializeLanguage();
		registerServices();
		logger.info("started");
	}

	@Override
	final protected void takeDown() {
		deregisterServices();
		finalizeGUI();
		logger.info("stopped");
	}

	protected void initializeGUI() {
	}

	protected void finalizeGUI() {
	}

	protected void initializeBehaviours() {
	}

	protected void initializeData() {
	}

	protected void initializeLanguage() {
	}

	protected void registerServices() {
	}

	protected void deregisterServices() {
	}

	private static final long serialVersionUID = -5660298497795218024L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
