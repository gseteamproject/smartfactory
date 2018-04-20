package salesMarketBehaviours;

import interactors.DeadlineBehaviour;
import interactors.OrderDataStore;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.ThreadedBehaviourFactory;

public class SalesMarketActivityBehaviour extends ParallelBehaviour {

    /**
     * 
     */
    private static final long serialVersionUID = -3030187281731033803L;
    
    private ThreadedBehaviourFactory tbf = new ThreadedBehaviourFactory();

    public SalesMarketActivityBehaviour(SalesMarketResponder interactionBehaviour, SalesMarketRequestResult interactor, OrderDataStore dataStore) {
        super(interactionBehaviour.getAgent(), WHEN_ANY);

        addSubBehaviour(new DeadlineBehaviour(interactionBehaviour, interactor, dataStore));
//        addSubBehaviour(new SalesMarketDeadlineBehaviour(interactionBehaviour, interactor, dataStore));
//        addSubBehaviour(new AskForOrderBehaviour((SalesMarketResponder) interactionBehaviour, dataStore));
        addSubBehaviour(tbf.wrap(new SalesMarketAskBehaviour(interactionBehaviour, interactor, dataStore)));
    }

}
