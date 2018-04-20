package interactors;

import jade.core.behaviours.OneShotBehaviour;

public class DecisionBehaviour extends OneShotBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = 5281539625153081963L;

    protected ResponderBehaviour interactionBehaviour;

    protected Decision interactor;

    public DecisionBehaviour(ResponderBehaviour interactionBehaviour) {
        this.interactionBehaviour = interactionBehaviour;
    }

    @Override
    public void action() {
        interactionBehaviour.setResponse(interactor.execute(interactionBehaviour.getRequest()));
    }

}
