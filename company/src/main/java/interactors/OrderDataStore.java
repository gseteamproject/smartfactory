package interactors;

import jade.core.behaviours.DataStore;
import jade.lang.acl.ACLMessage;

public class OrderDataStore extends DataStore {

    /**
     * 
     */
    private static final long serialVersionUID = 2340744686374901306L;

    public void setRequestMessage(ACLMessage msg) {
        put("request-message", msg);
    }
    
    public ACLMessage getRequestMessage() {
        return (ACLMessage) get("request-message");
    }
    
    public void setSubMessage(ACLMessage msg) {
        put("sub-message", msg);
    }
    
    public ACLMessage getSubMessage() {
        return (ACLMessage) get("sub-message");
    }
    
    public void setDeadline(long deadline) {
        put("deadline", deadline);
    }
    
    public long getDeadline() {
        return (long) get("deadline");
    }
    
    public void setAgent(String agentName) {
        put("agent", agentName);
    }
    
    public String getAgent() {
        return (String) get("agent");
    }
}
