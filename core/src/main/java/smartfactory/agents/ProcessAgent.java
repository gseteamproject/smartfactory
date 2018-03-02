package smartfactory.agents;

import smartfactory.behaviours.process.ProcessBehaviour;
import smartfactory.models.Process;
import smartfactory.models.ProcessOperation;

public class ProcessAgent extends BaseAgent {

	private static final long serialVersionUID = 4558559070184477446L;

	public Process createProcess() {
		return new Process();
	}

	@Override
	protected void setupData() {
		super.setupData();
		agentDataStore.setProcess(createProcess());
		agentDataStore.setProcessOperation(new ProcessOperation());
	}

	@Override
	protected void setupBehaviours() {
		super.setupBehaviours();
		addBehaviour(new ProcessBehaviour(agentDataStore));
	}
}
