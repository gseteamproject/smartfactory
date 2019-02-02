package financesBehaviours;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.AgentDataStore;
import interactors.ResponderBehaviour;
import jade.core.behaviours.OneShotBehaviour;

public class TransferMoneyFromBank extends OneShotBehaviour {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final long serialVersionUID = -8355136234160671855L;

	private ResponderBehaviour interactionBehaviour;

	private AgentDataStore dataStore;

	public TransferMoneyFromBank(ResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		this.interactionBehaviour = interactionBehaviour;
		this.dataStore = dataStore;
	}

	@Override
	public void action() {
		logger.info("Buy {}", dataStore.getOrder().getTextOfOrder());
		interactionBehaviour.getRequestResult().execute(interactionBehaviour.getRequest());
	}
}
