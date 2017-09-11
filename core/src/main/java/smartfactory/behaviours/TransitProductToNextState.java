package smartfactory.behaviours;

import jade.core.behaviours.Behaviour;

public class TransitProductToNextState extends ProductSubBehaviour {

	public TransitProductToNextState(Behaviour behaviour) {
		super(behaviour);
	}

	public static final int ProductIsInTheLastState = 0;
	public static final int ProductIsNotInTheLastState = 1;

	@Override
	public void action() {
		// TODO Auto-generated method stub
	}

	@Override
	public int onEnd() {
		return ProductIsInTheLastState;
	}

	private static final long serialVersionUID = -4593835134099389482L;
}
