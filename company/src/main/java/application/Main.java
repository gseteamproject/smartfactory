package application;

import communication.Communication;
import communication.Server;
import jade.Boot;

public class Main {

	private final static long DELAY_TIME_MS = 10; // delayTime for the GUI-Messages of the Agent-Communication

	public static void main(String[] args) {
		new Communication();
		// todo: maybe start the program, when the client is connected first?

		String[] parameters = new String[3];
		parameters[0] = "-gui";
		parameters[1] = addAgent("AgentCustomer", basicAgents.CustomerAgent.class)
				+ addAgent("AgentSalesMarket", basicAgents.SalesMarketAgent.class)
				+ addAgent("AgentSelling", basicAgents.SellingAgent.class)
				+ addAgent("AgentFinances", basicAgents.FinancesAgent.class)
				+ addAgent("AgentProcurement", basicAgents.ProcurementAgent.class)
				+ addAgent("AgentProcurementMarket", basicAgents.ProcurementMarketAgent.class)
				+ addAgent("AgentStoneSelling", basicAgents.SellerAgent.class, "Stone")
				+ addAgent("AgentPaintSelling", basicAgents.SellerAgent.class, "Paint")
				+ addAgent("AgentProduction", basicAgents.ProductionAgent.class)
				+ addAgent("sniffer", jade.tools.sniffer.Sniffer.class, "Agent*");

		parameters[2] = String.valueOf(DELAY_TIME_MS);
		Server.delaytime = DELAY_TIME_MS;

		Boot.main(parameters);
	}

	private static String addAgent(String agentName, Class<?> agentClass) {
		return agentName + ":" + agentClass.getName() + ";";
	}

	private static String addAgent(String agentName, Class<?> agentClass, String parameters) {
		return agentName + ":" + agentClass.getName() + "(" + parameters + ");";
	}
}
