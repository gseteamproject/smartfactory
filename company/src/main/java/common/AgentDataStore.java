package common;

import java.util.List;

import basicClasses.Order;
import jade.core.behaviours.DataStore;
import jade.lang.acl.ACLMessage;

public class AgentDataStore extends DataStore {

	private static final long serialVersionUID = 2340744686374901306L;

	public void setSubMessage(ACLMessage msg) {
		put("sub-message", msg);
	}

	public ACLMessage getSubMessage() {
		return (ACLMessage) get("sub-message");
	}

	public void setAgentName(String agentName) {
		put("agentName", agentName);
	}

	public String getAgentName() {
		return (String) get("agentName");
	}

	public void setDeadlineResult(boolean isDeadline) {
		put("is-deadline", isDeadline);
	}

	public boolean getDeadlineResult() {
		return (boolean) get("is-deadline");
	}

	public void setIsInMaterialStorage(boolean b) {
		put("isInMaterialStorage", b);
	}

	public boolean getIsInMaterialStorage() {
		return (boolean) get("isInMaterialStorage");
	}

	public void setIsGiven(boolean b) {
		put("isGiven", b);
	}

	public boolean getIsGiven() {
		return (boolean) get("isGiven");
	}

	public void setIsProduced(boolean b) {
		put("isProduced", b);
	}

	public boolean getIsProduced() {
		return (boolean) get("isProduced");
	}

	public void setIsTaken(boolean b) {
		put("isTaken", b);
	}

	public boolean getIsTaken() {
		return (boolean) get("isTaken");
	}

	public void setIsInWarehouse(boolean b) {
		put("isInWarehouse", b);
	}

	public boolean getIsInWarehouse() {
		return (boolean) get("isInWarehouse");
	}

	// queue for orders that in production
	public void setProductionQueue(List<Order> productionQueue) {
		put("productionQueue", productionQueue);
	}

	@SuppressWarnings("unchecked")
	public List<Order> getProductionQueue() {
		return (List<Order>) get("productionQueue");
	}

	public void setGoodName(String goodName) {
		put("goodName", goodName);
	}

	public String getGoodName() {
		return (String) get("goodName");
	}

	public void setAgentPlatform(AgentPlatform agentPlatform) {
		put("agentPlatform", agentPlatform);
	}

	public AgentPlatform getAgentPlatform() {
		return (AgentPlatform) get("agentPlatform");
	}

	public void setOrder(Order order) {
		put("order", order);
	}

	public Order getOrder() {
		return (Order) get("order");
	}
}
