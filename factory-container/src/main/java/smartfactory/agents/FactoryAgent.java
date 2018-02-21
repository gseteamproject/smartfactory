package smartfactory.agents;

import java.util.Vector;

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

	@Override
	public Resource createResource() {
		Resource machine = new Factory();
		machine.addOperation(new BlockProductionOperation());
		return machine;
	}

	private class BlockProductionOperation extends ResourceOperation {

		String processAgentName = BlockProcessAgent.getUniqueName();

		public BlockProductionOperation() {
			super("block-production");
		}

		Behaviour eventSubscribptionBehaviour = new EventSubcriptionInitiatorBehaviour();

		@Override
		public void execute() {
			executed = false;

			AgentConfiguration subAgentConfiguration = new AgentConfiguration();
			subAgentConfiguration.name = processAgentName;
			subAgentConfiguration.className = BlockProcessAgent.class.getName();
			agentDataStore.setSubAgentConfiguration(subAgentConfiguration);

			addBehaviour(new LaunchAgentBehaviour(agentDataStore));
			addBehaviour(eventSubscribptionBehaviour);

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
