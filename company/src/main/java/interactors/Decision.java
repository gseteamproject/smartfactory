package interactors;

import jade.lang.acl.ACLMessage;

public class Decision {
    protected OrderDataStore dataStore;
    protected ResponderBehaviour interactionBehaviour;

    public Decision(ResponderBehaviour interactionBehaviour, OrderDataStore dataStore) {
        this.dataStore = dataStore;
        this.interactionBehaviour = interactionBehaviour; 
    }

    public ACLMessage execute(ACLMessage request) {
        return null;
    }
}
