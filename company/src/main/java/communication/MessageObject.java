package communication;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class MessageObject {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private String sender;

	private String receiver;

	private String orderText;

	private String performative;

	private String message;

	public MessageObject(ACLMessage acl, String orderText) {
		this.setOrderText(orderText);
		this.setPerformative(acl.getPerformative());
		this.setSender(acl.getSender().getLocalName());
		this.setReceiver(((AID) acl.getAllReceiver().next()).getLocalName());
	}

	public MessageObject(String actingAgent, String actionMessage) {
		this.sender = actingAgent;
		this.orderText = actionMessage;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
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

	public void setPerformative(int performative) {
		this.performative = ACLMessage.getPerformative(performative);
	}

	private static final Map<String, String> colorForAgent = new HashMap<String, String>();
	static {
		colorForAgent.put("AgentProcurement", "#3CAD00");
		colorForAgent.put("AgentProcurementMarket", "#52EA00");
		colorForAgent.put("AgentCapitalMarket", "#00A6C4");
		colorForAgent.put("AgentPaintSelling", "#C40000");
		colorForAgent.put("AgentSelling", "#F2EE00");
		colorForAgent.put("AgentStoneSelling", "#8EB19D");
		colorForAgent.put("AgentSalesMarket", "#BC00BC");
		colorForAgent.put("AgentProduction", "#A0AF79");
		colorForAgent.put("AgentFinances", "#006863");
	}

	public String getColorForAgent() {
		logger.info("getReceiver {}", this.getReceiver());

		String color = colorForAgent.get(receiver);
		if (color == null) {
			return "#000000";
		}
		return color;
	}

	private static final Map<String, String> colorForAction = new HashMap<String, String>();
	static {
		colorForAction.put("AgentProcurement", "orange");
		colorForAction.put("AgentProcurementMarket", "#0096fa");
		colorForAction.put("AgentCapitalMarket", "#111111");
		colorForAction.put("AgentPaintSelling", "#0aff96");
		colorForAction.put("AgentSelling", "#dc96be");
		colorForAction.put("AgentStoneSelling", "#0aff96");
		colorForAction.put("AgentSalesMarket", "red");
		colorForAction.put("AgentProduction", "#00be00");
		colorForAction.put("AgentFinances", "#dcd201");
	}

	public String getColorForAction() {
		String color = colorForAction.get(sender);
		if (color == null) {
			return "#000000";
		}
		return color;
	}

	public String getColorForPerformative() {
		if ("ACCEPT_PROPOSAL".equals(performative)) {
			return "3CAD00";
		}
		if ("AGREE".equals(performative)) {
			return "52EA00";
		}
		if ("CANCEL".equals(performative)) {
			return "00A6C4";
		}
		if ("FAILURE".equals(performative)) {
			return "C40000";
		}
		if ("INFORM".equals(performative)) {
			return "F2EE00";
		}
		if ("REFUSE".equals(performative)) {
			return "8EB19D";
		}
		if ("REQUEST".equals(performative)) {
			return "BC00BC";
		}
		return "FFFFFF";
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReceivedMessage() {
		return String.format("%s received a Message of Type [%s] from %s. Order: %s;", receiver, performative, sender,
				orderText);
	}

	public String getActionMessage() {
		return sender + ": " + orderText;
	}
}
