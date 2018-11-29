package communication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class MessageObject {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ACLMessage aclmsg;
    private String sender;
    private String receiver;
    private String orderText;
    private String performative;
    private String message;
    private String receivedMessage;

    private String actingAgent;
    private String actionMessage;

    public MessageObject (ACLMessage acl, String orderText){
        this.aclmsg = acl;
        this.setOrderText(orderText);
        this.setPerformative();
        this.setSender();
        this.setReceiver();
        this.setReceivedMessage();
        
        this.actingAgent = this.getSender();
        this.actionMessage = actingAgent + ": " + orderText;
    }

    public MessageObject(String actingAgent, String actionMessage){
        this.actingAgent = actingAgent;
        this.actionMessage = actingAgent + ": " + actionMessage;
    }

   // public MessageObject (String manualSender, String manualMessage){this.message = manualSender + manualMessage;}

    public ACLMessage getAclmsg() {
        return aclmsg;
    }

    public void setAclmsg(ACLMessage aclmsg) {
        this.aclmsg = aclmsg;
    }

    public String getSender() {
        return sender;
    }

    public void setSender() {
        this.sender = this.aclmsg.getSender().getLocalName();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver() {
        this.receiver = ((AID) this.aclmsg.getAllReceiver().next()).getLocalName();
    }

    public String getOrderText() {
        return orderText;
    }

    public void setOrderText(String message) {
        this.orderText = message;
    }

    public String getPerformative() {
        return performative;
    }

    public void setPerformative() {
        switch (this.aclmsg.getPerformative()) {
            case 0: this.performative = "ACCEPT_PROPOSAL";
                break;
            case 1: this.performative = "AGREE";
                break;
            case 2: this.performative = "CANCEL";
                break;
            case 6: this.performative = "FAILURE";
                break;
            case 7: this.performative = "INFORM";
                break;
            case 14: this.performative = "REFUSE";
                break;
            case 16: this.performative = "REQUEST";
                break;
            default: this.performative = "UNKNOWN";
                break;
        }
    }

    public String getColorForAgent() {
        String color = "";
        logger.info("getReceiver {}", this.getReceiver());
        
        if (this.receiver.equals("AgentProcurement")) {
            color = "#3CAD00";
        }
        else if (this.receiver.equals("AgentProcurementMarket")) {
            color = "#52EA00";
        }
        else if (this.receiver.equals("AgentCapitalMarket")) {
            color = "#00A6C4";
        }
        else if (this.receiver.equals("AgentPaintSelling")) {
            color = "#C40000";
        }
        else if (this.receiver.equals("AgentSelling")) {
            color = "#F2EE00";
        }
        else if (this.receiver.equals("AgentStoneSelling")) {
            color = "#8EB19D";
        }
        else if (this.receiver.equals("AgentSalesMarket")) {
            color = "#BC00BC";
        }
        else if (this.receiver.equals("AgentProduction")) {
            color = "#A0AF79";
        }
        else if (this.receiver.equals("AgentFinances")) {
            color = "#006863";
        }
        else {
            color = "#000000";
        }

        return color;
    }

    public String getColorForAction() {
        String color = "";

        if (this.actingAgent.equals("AgentProcurement")) {
            color = "orange";
        }
        else if (this.actingAgent.equals("AgentProcurementMarket")) {
//            color = "lightblue";
            color = "#0096fa";
        }
        else if (this.actingAgent.equals("AgentCapitalMarket")) {
//            color = "#00A6C4";
            color = "#111111";
        }
        else if (this.actingAgent.equals("AgentPaintSelling")) {
//            color = "#C40000";
            color = "#0aff96";
        }
        else if (this.actingAgent.equals("AgentSelling")) {
//            color = "pink";
            color = "#dc96be";
        }
        else if (this.actingAgent.equals("AgentStoneSelling")) {
//            color = "#8EB19D";
            color = "#0aff96";
        }
        else if (this.actingAgent.equals("AgentSalesMarket")) {
            color = "red";
        }
        else if (this.actingAgent.equals("AgentProduction")) {
//            color = "lightgreen";
            color = "#00be00";
        }
        else if (this.actingAgent.equals("AgentFinances")) {
//            color = "yellow";
            color = "#dcd201";
        }
        else {
            color = "#000000";
        }

        return color;
    }

    public String getColorForPerformative() {
        String color = "";

        if (this.performative.equals("ACCEPT_PROPOSAL")) {
            color = "3CAD00";
        }
        else if (this.performative.equals("AGREE")) {
            color = "52EA00";
        }
        else if (this.performative.equals("CANCEL")) {
            color = "00A6C4";
        }
        else if (this.performative.equals("FAILURE")) {
            color = "C40000";
        }
        else if (this.performative.equals("INFORM")) {
            color = "F2EE00";
        }
        else if (this.performative.equals("REFUSE")) {
            color = "8EB19D";
        }
        else if (this.performative.equals("REQUEST")) {
            color = "BC00BC";
        }
        else {
            color = "FFFFFF";
        }

        return color;
    }

    public String getMessage (){
        return this.message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public void setReceivedMessage() {
        receivedMessage = this.receiver + " received a Message of Type [" + this.performative + "] from " + this.sender + ". Order: " + this.orderText + "; ";
    }
    public String getReceivedMessage (){
        return receivedMessage;
    }

    public String getActingAgent() {
        return actingAgent;
    }
    public String getActionMessage() {
        return actionMessage;
    }

  /*  public String actionMessage(){
       String msg = this.actingAgent + " " + this.actionMessage;
        return msg;
    }*/
}