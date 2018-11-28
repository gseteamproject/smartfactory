package smartfactory.views;

import smartfactory.presenters.CustomerPresenter;

public class CustomerView extends javax.swing.JFrame {

	private CustomerPresenter presenter;

	public CustomerView() {
		initComponents();
	}

	public CustomerView(CustomerPresenter presenter) {
		this();
		this.presenter = presenter;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		CreateOrderButton = new javax.swing.JButton();
		OrderStatus = new javax.swing.JTextField();

		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		setTitle("Customer View");
		setResizable(false);
		setSize(new java.awt.Dimension(250, 70));
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				formWindowClosing(evt);
			}
		});

		CreateOrderButton.setText("Create Order");
		CreateOrderButton.setToolTipText("Create Order to process blocks");
		CreateOrderButton.setName(""); // NOI18N
		CreateOrderButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				CreateOrderButtonActionPerformed(evt);
			}
		});

		OrderStatus.setEditable(false);
		OrderStatus.setText("not started");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(CreateOrderButton)
						.addGap(18, 18, 18)
						.addComponent(OrderStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(CreateOrderButton).addComponent(OrderStatus,
										javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(59, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void CreateOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_CreateOrderButtonActionPerformed
		presenter.createOrder();
		OrderStatus.setText("started");
	}// GEN-LAST:event_CreateOrderButtonActionPerformed

	private void formWindowClosing(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_formWindowClosing
		presenter.destroy();
	}// GEN-LAST:event_formWindowClosing

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton CreateOrderButton;
	private javax.swing.JTextField OrderStatus;
	// End of variables declaration//GEN-END:variables

	private static final long serialVersionUID = -2309982889289916539L;

	public void showOrderIsCompleted() {
		OrderStatus.setText("completed");
	}

	public void showOrderIsFailed() {
		OrderStatus.setText("failed");
	}
}
