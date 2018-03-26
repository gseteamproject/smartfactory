package smartfactory.usecase;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.SimpleBehaviour;
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

		ACLMessage message1 = new ACLMessage(ACLMessage.SUBSCRIBE);
		message1.setProtocol(FIPANames.InteractionProtocol.FIPA_SUBSCRIBE);
		message1.setConversationId("process-status");
		testflow.addSubBehaviour(sendMessage(order, message1));

		MessageTemplate pattern1 = MessageTemplate.and(MessageTemplate.MatchConversationId("process-status"),
				MessageTemplate.MatchPerformative(ACLMessage.AGREE));
		testflow.addSubBehaviour(waitMessage(pattern1));

		MessageTemplate pattern2 = MessageTemplate.and(MessageTemplate.MatchConversationId("process-status"),
				MessageTemplate.MatchPerformative(ACLMessage.INFORM));
		testflow.addSubBehaviour(waitMessage(pattern2, Event.PROCESS_COMPLETED_SUCCESS));

		testflow.addSubBehaviour(assertUnhandledMessages());

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

	private Behaviour sendMessage(TestingAgent receiver, ACLMessage message) {
		return new OneShotBehaviour() {
			private static final long serialVersionUID = -4352165018377538440L;

			@Override
			public void action() {
				receiver.receiveMessage(message);
			}
		};
	}

	private Behaviour waitMessage(MessageTemplate pattern, String content) {
		return new SimpleBehaviour() {
			private static final long serialVersionUID = -6230889827519202528L;

			@Override
			public void action() {
				ACLMessage message = myAgent.receive(pattern);
				if (message != null) {
					isDone = true;
					if (content != null) {
						String result = message.getContent();
						if (result.compareTo(content) != 0) {
							failed(String.format("mismatch content, expected: %s, actual: %s ", content, result));
						}
					}
				} else {
					block();
				}
			}

			private boolean isDone;

			@Override
			public boolean done() {
				return isDone;
			}
		};
	}

	private Behaviour waitMessage(MessageTemplate pattern) {
		return waitMessage(pattern, null);
	}

	private Behaviour assertUnhandledMessages() {
		return new OneShotBehaviour() {
			private static final long serialVersionUID = 4918689978403220156L;

			@Override
			public void action() {
				ACLMessage message = myAgent.receive();
				if (message == null) {
					passed("done");
				} else {
					failed("unhandled message");
				}
			}
		};
	}
}
