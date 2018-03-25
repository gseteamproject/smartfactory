package smartfactory.usecase;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import smartfactory.agents.CleaningStationAgent;
import smartfactory.agents.FactoryAgent;
import smartfactory.agents.LegoAgent;
import smartfactory.agents.OrderProcessAgent;
import smartfactory.agents.PaintingStationAgent;
import smartfactory.agents.WarehouseAgent;
import smartfactory.application.IntegrationTests;
import smartfactory.models.Event;
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

	private TestingAgent lego1;

	private TestingAgent lego2;

	private TestingAgent order;

	@Override
	public Behaviour load(Agent tester) throws TestException {
		setTimeout(IntegrationTests.TEST_TIMEOUT);

		factory = new TestingAgent(tester, "factory", FactoryAgent.class);
		warehouse = new TestingAgent(tester, "warehouse", WarehouseAgent.class);
		cleaningStation = new TestingAgent(tester, "cleaning-station", CleaningStationAgent.class);
		paintingStation = new TestingAgent(tester, "painting-station", PaintingStationAgent.class);
		lego1 = new TestingAgent(tester, "lego-1", LegoAgent.class);
		lego2 = new TestingAgent(tester, "lego-2", LegoAgent.class);
		order = new TestingAgent(tester, OrderProcessAgent.getUniqueName(), OrderProcessAgent.class);

		factory.start();
		warehouse.start();
		cleaningStation.start();
		paintingStation.start();
		lego1.start();
		lego2.start();
		TestHelpers.waitDFAgentTimeout();

		SequentialBehaviour testflow = new SequentialBehaviour();
		testflow.addSubBehaviour(new OneShotBehaviour() {
			private static final long serialVersionUID = 8080897193054917900L;

			@Override
			public void action() {
				try {
					order.start();
				} catch (TestException e) {
					failed(e.getMessage());
				}
			}
		});
		testflow.addSubBehaviour(new OneShotBehaviour() {
			private static final long serialVersionUID = -323490842116977398L;

			@Override
			public void action() {
				ACLMessage message = new ACLMessage(ACLMessage.SUBSCRIBE);
				message.setProtocol(FIPANames.InteractionProtocol.FIPA_SUBSCRIBE);
				message.setConversationId("process-status");

				order.receiveMessage(message);
			}
		});
		testflow.addSubBehaviour(new CyclicBehaviour() {
			private static final long serialVersionUID = 7523474841375429339L;

			private MessageTemplate pattern = MessageTemplate.and(MessageTemplate.MatchConversationId("process-status"),
					MessageTemplate.MatchPerformative(ACLMessage.INFORM));

			@Override
			public void action() {
				ACLMessage message = myAgent.receive(pattern);
				if (message != null) {
					String result = message.getContent();
					if (result.compareTo(Event.PROCESS_COMPLETED_SUCCESS) == 0) {
						passed("done");
					} else {
						failed("failed");
					}
				} else {
					block();
				}
			}
		});

		return testflow;
	}

	@Override
	public void clean(Agent tester) {
		try {
			lego1.stop();
			lego2.stop();
			cleaningStation.stop();
			paintingStation.stop();
			warehouse.stop();
			factory.stop();
		} catch (TestException e) {
			e.printStackTrace();
		}
	}
}
