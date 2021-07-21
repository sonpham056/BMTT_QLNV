package app.gui.user;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import app.bus.ReportBUS;
import app.bus.UserBUS;
import app.bus.services.SystemServices;
import app.bus.viewbag.ViewBag;
import app.dto.Report;
import app.dto.User;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class PnSendReport extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtTo;
	private JTextField txtTitle;
	private JTextArea txtContent;

	public PnSendReport() {
		setBackground(new Color(204, 204, 255));
		setBounds(0, 0, 674, 503);
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnTop = new JPanel();
		pnTop.setBackground(new Color(204, 204, 255));
		pnTop.setPreferredSize(new Dimension(10, 120));
		add(pnTop, BorderLayout.NORTH);
		pnTop.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("To:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 10, 70, 42);
		pnTop.add(lblNewLabel);
		
		txtTo = new JTextField();
		txtTo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTo.setBounds(90, 10, 574, 42);
		pnTop.add(txtTo);
		txtTo.setColumns(10);
		
		txtTitle = new JTextField();
		txtTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTitle.setColumns(10);
		txtTitle.setBounds(90, 62, 574, 42);
		pnTop.add(txtTitle);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setBounds(10, 62, 70, 42);
		pnTop.add(lblTitle);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		txtContent = new JTextArea();
		txtContent.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPane.setViewportView(txtContent);
		
		JPanel pnBottom = new JPanel();
		pnBottom.setBackground(new Color(204, 204, 255));
		pnBottom.setPreferredSize(new Dimension(10, 50));
		add(pnBottom, BorderLayout.SOUTH);
		pnBottom.setLayout(null);
		
		JButton btnSend = new JButton("Send");
		btnSend.setContentAreaFilled(false);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSendClicked();
			}
		});
		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSend.setBounds(529, 10, 135, 30);
		pnBottom.add(btnSend);
	}
	//===============================================================================================================================================
	private void btnSendClicked() {
		try {
			checkInput();
			User currentUser = ViewBag.getUser();
			//check is this user have permission to do this
			if (!currentUser.getAuthorizationTable().isReport()) {
				throw new Exception("You do not have the authority to do this!");
			}
			if (JOptionPane.showConfirmDialog(this, "Send report?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				User receiver = checkUser();
				Report report = new Report(txtTitle.getText(), txtContent.getText(),new Date(), false, currentUser, receiver);
				
				ReportBUS.add(report);
				
				if (ViewBag.isAudit && currentUser.isFollowedByAdmin()) {
					SystemServices.addAuditHistory(currentUser, 9);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
	}
	//===============================================================================================================================================
	private void checkInput() throws Exception {
		if (txtTitle.getText().isBlank() || txtTo.getText().isBlank()) {
			throw new Exception("please fill all required fields");
		}
	}
	
	private User checkUser() throws Exception {
		User user = UserBUS.getByEmail(txtTo.getText());
		if (user == null) {
			throw new Exception("User not found!");
		}
		return user;
	}
}
