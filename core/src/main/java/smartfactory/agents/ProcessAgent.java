package smartfactory.agents;

import smartfactory.behaviours.process.ProcessBehaviour;
import smartfactory.models.Process;
import smartfactory.models.ProcessOperation;

public class ProcessAgent extends BaseAgent {

	private static final long serialVersionUID = 4558559070184477446L;

	@Override
	protected void setupData() {
		super.setupData();
		agentDataStore.setProcess(createProcess());
		agentDataStore.setProcessOperation(new ProcessOperation());
	}

	public Process createProcess() {
		return new Process();
	}

	@Override
	protected void setupSpecialBehaviours() {
		addBehaviour(new ProcessBehaviour(agentDataStore));
	}
}
