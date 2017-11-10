package smartfactory.behaviours.machine;

import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.ThreadedBehaviourFactory;
import smartfactory.behaviours.machine.ActivityResponder;
import smartfactory.dataStores.MachineDataStore;

public class Activity extends ParallelBehaviour {

	private ThreadedBehaviourFactory tbf = new ThreadedBehaviourFactory();

	public Activity(ActivityResponder interactionBehaviour, MachineDataStore machineDataStore) {
		super(interactionBehaviour.getAgent(), WHEN_ANY);
		addSubBehaviour(tbf.wrap(new Work(interactionBehaviour, machineDataStore)));
		addSubBehaviour(new Status(interactionBehaviour, machineDataStore));
		addSubBehaviour(new Deadline(interactionBehaviour, machineDataStore));
	}

	private static final long serialVersionUID = -987827733291008766L;
}
