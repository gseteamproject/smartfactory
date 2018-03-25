package smartfactory.usecase;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import smartfactory.application.IntegrationTests;
import smartfactory.utility.TestHelpers;
import smartfactory.utility.TestingAgent;
import test.common.Test;
import test.common.TestException;

public class BlockProductionTest extends Test {
	private static final long serialVersionUID = 3042735222225099514L;

	private TestingAgent factory;

	private TestingAgent warehouse;

	private TestingAgent cleaningStation;

	private TestingAgent paintingStation;

	@Override
	public Behaviour load(Agent tester) throws TestException {
		setTimeout(IntegrationTests.TEST_TIMEOUT);

		factory = new TestingAgent(tester, "factory", smartfactory.agents.FactoryAgent.class);
		warehouse = new TestingAgent(tester, "warehouse", smartfactory.agents.WarehouseAgent.class);
		cleaningStation = new TestingAgent(tester, "cleaning-station", smartfactory.agents.CleaningStationAgent.class);
		paintingStation = new TestingAgent(tester, "painting-station", smartfactory.agents.PaintingStationAgent.class);

		factory.start();
		warehouse.start();
		cleaningStation.start();
		paintingStation.start();
		TestHelpers.waitDFAgentTimeout();

		return new OneShotBehaviour() {
			private static final long serialVersionUID = 8080897193054917900L;

			@Override
			public void action() {
				passed("done");
			}
		};
	}

	@Override
	public void clean(Agent tester) {
		try {
			cleaningStation.stop();
			paintingStation.stop();
			warehouse.stop();
			factory.stop();
		} catch (TestException e) {
			e.printStackTrace();
		}
	}
}
