package interactors;

import jade.lang.acl.ACLMessage;

public class RequestResult {
    protected OrderDataStore dataStore;
    public boolean isDone;

    public RequestResult(OrderDataStore dataStore) {
        super();
        this.dataStore = dataStore;
    }
    
    public ACLMessage execute(ACLMessage request) {
        return null;
    }
    
    public boolean done() {
        return isDone;
    }
    
    public void reset() {
        this.isDone = false;
    }
}