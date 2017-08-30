package smartfactory.remote.views;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class CustomerView extends JFrame {
	public CustomerView() {
		setTitle("Customer");
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton = new JButton("Create Block");
		getContentPane().add(btnNewButton);
	}

	private static final long serialVersionUID = -4295393472554100657L;
}
