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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CreateOrderButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Customer View");
        setResizable(false);
        setSize(new java.awt.Dimension(250, 70));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        CreateOrderButton.setText("Create Order");
        CreateOrderButton.setToolTipText("Create Order to process blocks");
        CreateOrderButton.setName(""); // NOI18N
        CreateOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateOrderButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CreateOrderButton)
                .addContainerGap(115, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CreateOrderButton)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CreateOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateOrderButtonActionPerformed
        presenter.createOrder();
    }//GEN-LAST:event_CreateOrderButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        presenter.destroy();
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CreateOrderButton;
    // End of variables declaration//GEN-END:variables

	private static final long serialVersionUID = -2309982889289916539L;
}