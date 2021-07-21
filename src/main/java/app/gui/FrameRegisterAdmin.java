package app.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import app.bus.AuthorizationTableBUS;
import app.bus.UserBUS;
import app.bus.services.ValidateCheck;
import app.dto.AuthorizationTable;
import app.dto.Role;
import app.dto.User;
import java.awt.Toolkit;
import java.awt.Color;

public class FrameRegisterAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtEmail;
	private JTextField txtName;
	private JPasswordField txtPass;
	private JPasswordField txtConfirmPass;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
					FrameRegisterAdmin frame = new FrameRegisterAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameRegisterAdmin() {
		getContentPane().setBackground(new Color(255, 204, 204));
		setIconImage(Toolkit.getDefaultToolkit().getImage("images\\main.png"));
		
		setBounds(0, 0, 550, 440);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Email (user name)");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEADING);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 25, 155, 48);
		getContentPane().add(lblNewLabel);
		
		txtEmail = new JTextField();
		txtEmail.setText("admin@gmail.com");
		txtEmail.setEnabled(false);
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtEmail.setColumns(10);
		txtEmail.setBounds(175, 30, 315, 36);
		getContentPane().add(txtEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.LEADING);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setBounds(10, 177, 155, 48);
		getContentPane().add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm password");
		lblConfirmPassword.setHorizontalAlignment(SwingConstants.LEADING);
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblConfirmPassword.setBounds(10, 261, 155, 48);
		getContentPane().add(lblConfirmPassword);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.LEADING);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblName.setBounds(10, 98, 155, 48);
		getContentPane().add(lblName);
		
		txtName = new JTextField();
		txtName.setText("admin");
		txtName.setEnabled(false);
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtName.setColumns(10);
		txtName.setBounds(175, 103, 315, 36);
		getContentPane().add(txtName);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setContentAreaFilled(false);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCreateClicked();
			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCreate.setBounds(111, 336, 134, 54);
		getContentPane().add(btnCreate);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setContentAreaFilled(false);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnExitClicked();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExit.setBounds(285, 336, 134, 54);
		getContentPane().add(btnExit);
		
		txtPass = new JPasswordField();
		txtPass.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPass.setBounds(175, 182, 315, 36);
		getContentPane().add(txtPass);
		
		txtConfirmPass = new JPasswordField();
		txtConfirmPass.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtConfirmPass.setBounds(175, 266, 315, 36);
		getContentPane().add(txtConfirmPass);
	}
	
	private void btnCreateClicked() {
		try {
			checkIsInputEmpty();
			checkValidInfo();
			checkIsPasswordMatch();
			if (checkIsUserExist() != null) {
				throw new Exception("This user has already exist");
			}
			
			String email = txtEmail.getText();
			String pass = new String(txtPass.getPassword());
			
			String sha = SHA.toHexString(email, pass);
			AuthorizationTable author = new AuthorizationTable(true, true, true);
			int authorizationTableId = AuthorizationTableBUS.add(author);
			User user = new User(txtEmail.getText(), sha, txtName.getText(), null, null, new AuthorizationTable(authorizationTableId), new Role(1), true);
			int id = UserBUS.addAdmin(user);
			JOptionPane.showMessageDialog(this, "User added " + id);
			isBlank();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	private void checkIsInputEmpty() throws Exception {
		if (txtEmail.getText().isBlank() || txtPass.getPassword().toString().isBlank()
				|| txtConfirmPass.getPassword().toString().isBlank() || txtName.getText().isBlank()) {
			throw new Exception("Please provide all of the information");
		}
	}

	private void checkIsPasswordMatch() throws Exception {
		String pass = new String(txtPass.getPassword());
		String confirm = new String(txtConfirmPass.getPassword());
		if (pass.compareTo(confirm) != 0) {
			throw new Exception("Password missed match");
		}
	}
	
	private void checkValidInfo() throws Exception {
		ValidateCheck.checkEmail(txtEmail.getText());
	}
	
	private User checkIsUserExist() throws Exception{
		return UserBUS.getByEmail(txtEmail.getText());
	}
	
	private void btnExitClicked() {
		this.dispose();
	}
	
	private void isBlank() {
		txtEmail.setText("");
		txtName.setText("");
		txtPass.setText("");
		txtConfirmPass.setText("");
	}
}

