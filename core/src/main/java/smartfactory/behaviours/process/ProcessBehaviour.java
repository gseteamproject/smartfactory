package smartfactory.behaviours.process;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.FSMBehaviour;
import smartfactory.models.ProcessOperation;
import smartfactory.serviceProvisioning.behaviours.ServiceProvisioningInitiatorBehaviour;
import smartfactory.utility.AgentDataStore;
import smartfactory.models.Process;

public class ProcessBehaviour extends FSMBehaviour {

	public ProcessBehaviour(AgentDataStore dataObject) {
		super();

		Behaviour b1 = new DetermineRequiredServiceBehaviour(dataObject);
		Behaviour b2 = new FindAgentsProvidingServiceBehaviour(dataObject);
		Behaviour b3 = new SelectAgentToPerformServiceBehaviour(dataObject);
		Behaviour b4 = new ServiceProvisioningInitiatorBehaviour(dataObject);
		Behaviour b5 = new TransitProcessToNextOperationBehaviour(dataObject);
		Behaviour b6 = new ProcessIsCompletedBehaviour(dataObject);
		Behaviour b7 = new ProcessIsIncorrectBehaviour(dataObject);
		Behaviour b8 = new NoAgentsProvidingServiceBehaviour(dataObject);

		int b1_b2 = ProcessOperation.ServiceDetermined;
		int b1_b7 = ProcessOperation.ServiceNotDetermined;
		int b2_b3 = ProcessOperation.AgentsFound;
		int b2_b8 = ProcessOperation.AgentsNotFound;
		int b3_b4 = ProcessOperation.AgentSelected;
		int b3_b2 = ProcessOperation.AgentNotSelected;
		int b4_b5 = ProcessOperation.ServicePerformedSuccessfully;
		int b4_b3 = ProcessOperation.ServicePerformedUnSuccessfully;
		int b5_b6 = Process.IsCompleted;
		int b5_b1 = Process.IsNotCompleted;

		String b1_name = b1.getBehaviourName();
		String b2_name = b2.getBehaviourName();
		String b3_name = b3.getBehaviourName();
		String b4_name = b4.getBehaviourName();
		String b5_name = b5.getBehaviourName();
		String b6_name = b6.getBehaviourName();
		String b7_name = b7.getBehaviourName();
		String b8_name = b8.getBehaviourName();

		registerFirstState(b1, b1_name);
		registerState(b2, b2_name);
		registerState(b3, b3_name);
		registerState(b4, b4_name);
		registerState(b5, b5_name);
		registerLastState(b6, b6_name);
		registerLastState(b7, b7_name);
		registerLastState(b8, b8_name);

		registerTransition(b1_name, b2_name, b1_b2);
		registerTransition(b1_name, b7_name, b1_b7);
		registerTransition(b2_name, b3_name, b2_b3);
		registerTransition(b2_name, b8_name, b2_b8);
		registerTransition(b3_name, b4_name, b3_b4);
		registerTransition(b3_name, b2_name, b3_b2, new String[] { b2_name, b3_name });
		registerTransition(b4_name, b5_name, b4_b5);
		registerTransition(b4_name, b3_name, b4_b3, new String[] { b3_name, b4_name });
		registerTransition(b5_name, b6_name, b5_b6);
		registerTransition(b5_name, b1_name, b5_b1, new String[] { b1_name, b2_name, b3_name, b4_name, b5_name });
	}

	private static final long serialVersionUID = -7091209844136813253L;
}
