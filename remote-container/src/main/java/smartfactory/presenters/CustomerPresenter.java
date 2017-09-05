package smartfactory.presenters;

import smartfactory.agents.CustomerAgent;
import smartfactory.views.CustomerView;

public class CustomerPresenter {

	CustomerView view;
	CustomerAgent agent;

	public CustomerPresenter(CustomerAgent agent) {
		this.agent = agent;
		this.view = new CustomerView(this);
	}

	public CustomerPresenter(CustomerAgent agent, CustomerView view) {
		this.agent = agent;
		this.view = view;
	}

	public void show() {
		view.setVisible(true);
	}

	public void hide() {
		view.dispose();
	}

	public void destroy() {
		agent.doDelete();
	}

	public void addBlock() {
		agent.addBlock();
	}
}
