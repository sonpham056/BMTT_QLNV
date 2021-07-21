package app.gui.user;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Color;

public class PnReport extends JPanel {
	private static final long serialVersionUID = 1L;

	public PnReport() {
		setBackground(new Color(204, 204, 255));
		setBounds(0, 0, 674, 503);
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(204, 204, 255));
		add(tabbedPane);
		

		tabbedPane.addTab("Send report", null, new PnSendReport(), null);
		tabbedPane.setBackgroundAt(0, new Color(204, 204, 255));
		tabbedPane.addTab("Receive report", null, new PnReceiveReport(), null);
	}

}
