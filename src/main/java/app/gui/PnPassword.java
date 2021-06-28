package app.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import app.bus.UserBUS;
import app.bus.services.SystemServices;
import app.bus.viewbag.ViewBag;
import app.dto.User;

public class PnPassword extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPasswordField txtOld;
	private JPasswordField txtNew;
	private JPasswordField txtConfirm;

	public PnPassword() {
		setBounds(0, 0, 673, 530);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Old password:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 134, 145, 60);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("New password:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(10, 204, 145, 60);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Confirm");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(10, 278, 145, 56);
		panel.add(lblNewLabel_1_2);
		
		txtOld = new JPasswordField();
		txtOld.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtOld.setBounds(165, 134, 498, 56);
		panel.add(txtOld);
		
		txtNew = new JPasswordField();
		txtNew.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNew.setBounds(165, 204, 498, 60);
		panel.add(txtNew);
		
		txtConfirm = new JPasswordField();
		txtConfirm.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtConfirm.setBounds(165, 278, 498, 56);
		panel.add(txtConfirm);
		
		JLabel lblNewLabel = new JLabel("Change your password");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel pnButton = new JPanel();
		pnButton.setPreferredSize(new Dimension(10, 50));
		add(pnButton, BorderLayout.SOUTH);
		pnButton.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnChangePassword = new JButton("Change password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnClicked();
			}
		});
		btnChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnButton.add(btnChangePassword);
	}
	//================================================================================================================================================================
	private void btnClicked() {
		try {
			checkIsInputEmptyAndPasswordMatch();
			if (JOptionPane.showConfirmDialog(this, "Change password?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				User user = ViewBag.getUser();
				//check is this user have permission to do this
				if (!user.getAuthorizationTable().isUserInfo()) {
					throw new Exception("You do not have the authority to do this!");
				}
				user.setPassword(new String(txtNew.getPassword()));
				
				UserBUS.update(user);
				
				if (ViewBag.isAudit && user.isFollowedByAdmin()) {
					SystemServices.addAuditHistory(user, 11);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
	}
	//================================================================================================================================================================
	
	private void checkIsInputEmptyAndPasswordMatch() throws Exception {
		if (txtConfirm.getPassword().toString().isBlank() || txtNew.getPassword().toString().isBlank() || txtOld.getPassword().toString().isBlank()) {
			throw new Exception("Please fill all the required fields");
		}
		User user = ViewBag.getUser();
		String oldPass = new String(txtOld.getPassword());
		if (oldPass.compareTo(user.getPassword()) != 0) {
			throw new Exception("Wrong password!");
		}
		String newPass = new String(txtNew.getPassword());
		String confirm = new String(txtConfirm.getPassword());
		if (newPass.compareTo(confirm) != 0) {
			throw new Exception("New password mismatch!");
		}
	}
}
