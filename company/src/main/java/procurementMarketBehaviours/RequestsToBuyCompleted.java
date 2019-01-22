package procurementMarketBehaviours;

import interactors.ResponderBehaviour;
import jade.core.behaviours.OneShotBehaviour;

public class RequestsToBuyCompleted extends OneShotBehaviour {

	private static final long serialVersionUID = -2857350551195866377L;

	private ResponderBehaviour interactionBehaviour;

	public RequestsToBuyCompleted(ResponderBehaviour interactionBehaviour) {
		super();
		this.interactionBehaviour = interactionBehaviour;
	}

	@Override
	public void action() {
		interactionBehaviour.getRequestResult().execute(interactionBehaviour.getRequest());
	}
}
