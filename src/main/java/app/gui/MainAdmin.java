package app.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import app.bus.UserBUS;
import app.bus.services.SystemServices;
import app.bus.viewbag.ViewBag;
import app.constant.SystemRole;
import app.dto.User;
import app.gui.admin.FrameAdmin;

public class MainAdmin extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField txtPassword;

	private JTextField txtUserName;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// HibernateUtil.getSessionFactory().openSession(); test connection
					//UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
					MainAdmin frame = new MainAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainAdmin() {
		//set audit on or off for view bag
		ViewBag.isAudit = SystemServices.checkSystemAudit();
		setResizable(false);
		setMaximumSize(new Dimension(1080, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel pnHeader = new JPanel();
		contentPane.add(pnHeader, BorderLayout.NORTH);
		pnHeader.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Qu\u1EA3n l\u00FD nh\u00E2n vi\u00EAn");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		pnHeader.add(lblNewLabel);

		JPanel pnMainBody = new JPanel();
		contentPane.add(pnMainBody, BorderLayout.CENTER);
		pnMainBody.setLayout(new BoxLayout(pnMainBody, BoxLayout.Y_AXIS));

		JPanel pnContainer = new JPanel();
		pnMainBody.add(pnContainer);
		pnContainer.setLayout(new BoxLayout(pnContainer, BoxLayout.Y_AXIS));

		JPanel pnSignIn = new JPanel();
		pnContainer.add(pnSignIn);
		pnSignIn.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 40));

		JPanel pnTaiKhoan = new JPanel();
		pnSignIn.add(pnTaiKhoan);
		pnTaiKhoan.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel_1 = new JLabel("User name");
		lblNewLabel_1.setPreferredSize(new Dimension(100, 20));
		lblNewLabel_1.setMinimumSize(new Dimension(50, 13));
		lblNewLabel_1.setMaximumSize(new Dimension(50, 13));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnTaiKhoan.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setPreferredSize(new Dimension(350, 34));
		pnTaiKhoan.add(panel_1);
		
		txtUserName = new JTextField();
		txtUserName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtUserName.setBounds(0, 0, 350, 34);
		panel_1.add(txtUserName);
		txtUserName.setColumns(10);

		JPanel pnMatKhau = new JPanel();
		pnSignIn.add(pnMatKhau);
		pnMatKhau.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setPreferredSize(new Dimension(100, 20));
		lblNewLabel_1_1.setMinimumSize(new Dimension(50, 13));
		lblNewLabel_1_1.setMaximumSize(new Dimension(50, 13));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnMatKhau.add(lblNewLabel_1_1);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(350, 34));
		pnMatKhau.add(panel);
		panel.setLayout(null);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPassword.setBounds(0, 0, 350, 34);
		panel.add(txtPassword);

		JPanel pnButton = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnButton.getLayout();
		flowLayout.setHgap(10);
		pnButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnSignIn.add(pnButton);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLoginClicked();
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnButton.add(btnLogin);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnExitClicked();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnButton.add(btnExit);

		JPanel pnFooter = new JPanel();
		pnMainBody.add(pnFooter);
		pnFooter.setLayout(new BoxLayout(pnFooter, BoxLayout.X_AXIS));

		JLabel lblNewLabel_2 = new JLabel("App developed by Micro-wave team");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnFooter.add(lblNewLabel_2);

		// set enter key
		this.getRootPane().setDefaultButton(btnLogin);
	}

	private void btnLoginClicked() {
		try {
			checkTxtBox();
			String password = new String(txtPassword.getPassword());
			User user = UserBUS.getLoginUser(txtUserName.getText(), password);
			if (user != null) {
				if (user.getRole().getRoleId() == SystemRole.ADMIN) {
					JOptionPane.showMessageDialog(this, "Login succeed! Welcome " + user.getName());
					ViewBag.currentUser = user;
					FrameAdmin frameAdmin = new FrameAdmin(user);
					frameAdmin.setVisible(true);

					//check if audit is on and is this user followed by admin?
					if (ViewBag.isAudit && user.isFollowedByAdmin()) {
						//write login history
						SystemServices.addAuditHistory(ViewBag.currentUser, 1);
					}
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(this, "You are not permitted here");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Login failed! please check your user name and password!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	private void btnExitClicked() {
		System.exit(0);
	}

	private void checkTxtBox() throws Exception {
		if (txtPassword.getPassword().toString().isBlank() || txtUserName.getText().isBlank()) {
			throw new Exception("Vui lòng nhập đủ thông tin đăng nhập!");
		}
	}
	
}
