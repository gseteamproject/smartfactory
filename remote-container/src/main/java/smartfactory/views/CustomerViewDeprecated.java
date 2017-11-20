package smartfactory.views;

import javax.swing.JFrame;

import smartfactory.presenters.CustomerPresenter;

import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Deprecated
public class CustomerViewDeprecated extends JFrame {

	private CustomerPresenter presenter;

	public CustomerViewDeprecated() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				presenter.destroy();
			}
		});

		setResizable(false);
		setTitle("Customer");
		setSize(200, 100);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnNewButton = new JButton();
		btnNewButton.setAction(actionAddBlock);
		getContentPane().add(btnNewButton);
	}

	public CustomerViewDeprecated(CustomerPresenter presenter) {
		this();
		this.presenter = presenter;
	}

	private final Action actionAddBlock = new SwingAction();

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Add Block");
			putValue(SHORT_DESCRIPTION, "Add Block to Factory");
		}

		public void actionPerformed(ActionEvent e) {
			presenter.addBlock();
		}

		private static final long serialVersionUID = -4172778835430238878L;
	}

	private static final long serialVersionUID = -4295393472554100657L;
}
