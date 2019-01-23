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

	private int performative;

	private String message;

	public MessageObject(ACLMessage acl, String orderText) {
		this(acl.getSender().getLocalName(), orderText);
		this.performative = acl.getPerformative();
		this.receiver = ((AID) acl.getAllReceiver().next()).getLocalName();
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
		return ACLMessage.getPerformative(performative);
	}

	public void setPerformative(int performative) {
		this.performative = performative;
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

	private static final Map<Integer, String> colorForPerformative = new HashMap<Integer, String>();
	static {
		colorForPerformative.put(ACLMessage.ACCEPT_PROPOSAL, "3CAD00");
		colorForPerformative.put(ACLMessage.AGREE, "52EA00");
		colorForPerformative.put(ACLMessage.CANCEL, "00A6C4");
		colorForPerformative.put(ACLMessage.FAILURE, "C40000");
		colorForPerformative.put(ACLMessage.INFORM, "F2EE00");
		colorForPerformative.put(ACLMessage.REFUSE, "8EB19D");
		colorForPerformative.put(ACLMessage.REQUEST, "BC00BC");
	}

	public String getColorForPerformative() {
		String color = colorForPerformative.get(performative);
		if (color == null) {
			return "FFFFFF";
		}
		return color;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getActionMessage() {
		return sender + ": " + orderText;
	}
}
