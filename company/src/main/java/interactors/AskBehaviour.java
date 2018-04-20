package interactors;

import jade.core.behaviours.SimpleBehaviour;

public class AskBehaviour extends SimpleBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = -4846116296001548998L;
    protected RequestResult interactor;
    protected ResponderBehaviour interactionBehaviour;
    protected OrderDataStore dataStore;

    protected boolean isStarted = false;

    public AskBehaviour(ResponderBehaviour interactionBehaviour, RequestResult interactor, OrderDataStore dataStore) {
        super(interactionBehaviour.getAgent());
        this.interactionBehaviour = interactionBehaviour;
        this.interactor = interactor;
        this.dataStore = dataStore;
    }

    @Override
    public void action() {
        
    }

    @Override
    public boolean done() {
        if (interactor.done()) {
            System.out.println("Done of " + interactionBehaviour.getAgent().getLocalName());
            interactionBehaviour.setResult(interactor.execute(interactionBehaviour.getRequest()));
        }
        return interactor.done();
    }

}
