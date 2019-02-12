package financesBehaviours;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.AgentDataStore;
import interactors.ResponderBehaviour;
import jade.core.behaviours.OneShotBehaviour;

public class TransferMoneyToBank extends OneShotBehaviour {

	private static final long serialVersionUID = 6133055540457867642L;

	private ResponderBehaviour interactionBehaviour;

	private AgentDataStore dataStore;

	public TransferMoneyToBank(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		this.interactionBehaviour = interactionBehaviour;
		this.dataStore = dataStore;
	}

	@Override
	public void action() {
		logger.info("Sell {}", dataStore.getOrder().getTextOfOrder());
		interactionBehaviour.getRequestResult().execute(interactionBehaviour.getRequest());
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
