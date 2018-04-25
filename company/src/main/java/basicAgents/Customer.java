package basicAgents;

import customerBehaviours.OneOrderBehaviour;
import jade.core.Agent;

public class Customer extends Agent {

    /**
     * 
     */
    private static final long serialVersionUID = -3320954039785467867L;

    @Override
    protected void setup() {
        // addBehaviour(new GenerateOrdersBehaviour(this, 15000));

        addBehaviour(new OneOrderBehaviour(this, 4000));
    }
}
