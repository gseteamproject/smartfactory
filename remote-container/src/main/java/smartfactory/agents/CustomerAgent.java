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
import smartfactory.presenters.CustomerPresenter;

public class CustomerAgent extends BaseAgent {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private CustomerPresenter presenter = new CustomerPresenter(this);

	@Override
	protected void setupData() {
		super.setupData();
	}

	@Override
	protected void setupGUI() {
		presenter.show();
	}

	@Override
	protected void takeDownGUI() {
		presenter.hide();
	}

	String processAgentName;

	public void createOrder() {
		processAgentName = OrderProcessAgent.getUniqueName();
		AgentConfiguration subAgentConfiguration = new AgentConfiguration();
		subAgentConfiguration.name = processAgentName;
		subAgentConfiguration.className = OrderProcessAgent.class.getName();
		agentDataStore.setSubAgentConfiguration(subAgentConfiguration);

		eventSubscribptionBehaviour = new EventSubcriptionInitiatorBehaviour();

		addBehaviour(new LaunchAgentBehaviour(agentDataStore));
		addBehaviour(eventSubscribptionBehaviour);
	}

	Behaviour eventSubscribptionBehaviour;

	private class EventSubcriptionInitiatorBehaviour extends SubscriptionInitiator {

		private static final long serialVersionUID = -8303012708422081629L;

		public EventSubcriptionInitiatorBehaviour() {
			super(null, null);
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		protected Vector prepareSubscriptions(ACLMessage subscription) {
			logger.debug("customer-agent subscription started");

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
				// TODO : show on the GUI that order is completed
				presenter.showOrderIsCompleted();

				cancel(inform.getSender(), false);
				cancel_inform = true;
			}
		}
	}

	private static final long serialVersionUID = -2432898217068138400L;
}
