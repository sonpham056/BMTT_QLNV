package app.gui.user;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;

public class PnReport extends JPanel {
	private static final long serialVersionUID = 1L;

	public PnReport() {
		setBounds(0, 0, 674, 503);
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);
		

		tabbedPane.addTab("Send report", null, new PnSendReport(), null);
		tabbedPane.addTab("Receive report", null, new PnReceiveReport(), null);
	}

}
