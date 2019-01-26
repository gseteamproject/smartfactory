package application;

import communication.ServerService;
import jade.Boot;

public class Main {

	public final static long SERVER_DELAY_TIME = 10;

	public static void main(String[] args) {
		String[] parameters = new String[] { "-gui", "-services",
				addService(jade.core.event.NotificationService.class)
						+ addService(communication.ServerService.class),
				addParameter(ServerService.DELAY_TIME), Long.toString(SERVER_DELAY_TIME),
				addAgent("AgentCustomer", basicAgents.CustomerAgent.class)
						+ addAgent("AgentSalesMarket", basicAgents.SalesMarketAgent.class)
						+ addAgent("AgentSelling", basicAgents.SellingAgent.class)
						+ addAgent("AgentFinances", basicAgents.FinancesAgent.class)
						+ addAgent("AgentProcurement", basicAgents.ProcurementAgent.class)
						+ addAgent("AgentProcurementMarket", basicAgents.ProcurementMarketAgent.class)
						+ addAgent("AgentStoneSelling", basicAgents.SellerAgent.class, "Stone")
						+ addAgent("AgentPaintSelling", basicAgents.SellerAgent.class, "Paint")
						+ addAgent("AgentProduction", basicAgents.ProductionAgent.class)
						+ addAgent("sniffer", jade.tools.sniffer.Sniffer.class, "Agent*") };

		Boot.main(parameters);
	}

	private static String addAgent(String agentName, Class<?> agentClass) {
		return agentName + ":" + agentClass.getName() + ";";
	}

	private static String addAgent(String agentName, Class<?> agentClass, String parameters) {
		return agentName + ":" + agentClass.getName() + "(" + parameters + ");";
	}

	private static String addService(Class<?> serviceClass) {
		return serviceClass.getName() + ";";
	}

	private static String addParameter(String name) {
		return "-" + name;
	};
}
