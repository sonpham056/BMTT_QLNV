package app.gui.admin;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class PnAuditHistory extends JPanel {
	private static final long serialVersionUID = 1L;
	private PnAuditHistoryAdmin pnAuditHistoryAdmin;
	private PnAuditHistoryUser pnAuditHistoryUser;

	public PnAuditHistory() {
		setBounds(0, 0, 673, 530);
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);
		
		pnAuditHistoryAdmin = new PnAuditHistoryAdmin();
		tabbedPane.addTab("Admin audit history", null, pnAuditHistoryAdmin, null);
		
		pnAuditHistoryUser = new PnAuditHistoryUser();
		tabbedPane.addTab("User audit history", null, pnAuditHistoryUser, null);
	}

}
