package smartfactory.agents;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.proto.SubscriptionInitiator;
import smartfactory.behaviours.base.LaunchAgentBehaviour;
import smartfactory.configuration.AgentConfiguration;
import smartfactory.models.Factory;
import smartfactory.models.Resource;
import smartfactory.models.ResourceOperation;

public class FactoryAgent extends ResourceAgent {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Resource createResource() {
		Resource machine = new Factory();
		machine.addOperation(new BlockProductionOperation());
		return machine;
	}

	private class BlockProductionOperation extends ResourceOperation {

		String processAgentName;

		public BlockProductionOperation() {
			super("block-production");
		}

		Behaviour eventSubscribptionBehaviour;

		@Override
		public void execute() {
			executed = false;
			processAgentName = BlockProcessAgent.getUniqueName();

			AgentConfiguration subAgentConfiguration = new AgentConfiguration();
			subAgentConfiguration.name = processAgentName;
			subAgentConfiguration.className = BlockProcessAgent.class.getName();
			agentDataStore.setSubAgentConfiguration(subAgentConfiguration);

			eventSubscribptionBehaviour = new EventSubcriptionInitiatorBehaviour();

			addBehaviour(new LaunchAgentBehaviour(agentDataStore));
			addBehaviour(eventSubscribptionBehaviour);

			// TODO : remove
			// executed = true;
			// operationCompleted();
		}

		private class EventSubcriptionInitiatorBehaviour extends SubscriptionInitiator {
			public EventSubcriptionInitiatorBehaviour() {
				super(null, null);
			}

			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			protected Vector prepareSubscriptions(ACLMessage subscription) {
				logger.debug("factory-agent subscription started");

				subscription = new ACLMessage(ACLMessage.SUBSCRIBE);
				subscription.addReceiver(new AID((processAgentName), AID.ISLOCALNAME));
				subscription.setProtocol(FIPANames.InteractionProtocol.FIPA_SUBSCRIBE);
				subscription.setConversationId("process-status");
				Vector l = new Vector(1);
				l.addElement(subscription);
				return l;
			}

			@Override
			protected void handleAgree(ACLMessage agree) {
			}

			@Override
			protected void handleRefuse(ACLMessage refuse) {
			}

			boolean cancel_inform = false;

			@Override
			protected void handleInform(ACLMessage inform) {
				if (cancel_inform) {
					cancellationCompleted(inform.getSender());
					removeBehaviour(eventSubscribptionBehaviour);
				} else {
					executed = true;
					operationCompleted();

					cancel(inform.getSender(), false);
					cancel_inform = true;
				}
			}

			private static final long serialVersionUID = -8553947845257751922L;
		}
	}

	private static final long serialVersionUID = 4282751471381265727L;
}
