package app.gui.admin;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import app.bus.services.SystemServices;
import app.bus.services.listenerclass.PanelAdminMouseClickListener;
import app.bus.viewbag.ViewBag;
import app.dto.User;
import app.gui.MainAdmin;
import app.gui.PnPassword;

import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Toolkit;

public class FrameAdmin extends JFrame {
	private static final long serialVersionUID = 1L;
	//JPanel
	private JPanel contentPane;
	private JPanel pnCardCenterAdmin;
	
	
	
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public FrameAdmin(User user) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("images\\admin.png"));
		setResizable(false);
		this.user = user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(204, 204, 255));
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Go to");
		mnNewMenu.setBackground(new Color(204, 204, 255));
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmManageUser = new JMenuItem("Manage User");
		mntmManageUser.setForeground(new Color(0, 0, 0));
		mntmManageUser.setBackground(new Color(204, 204, 255));
		mntmManageUser.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmManageUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnCreateUserClicked();
			}
		});
		mnNewMenu.add(mntmManageUser);
		
		JMenuItem mntmFollowUser = new JMenuItem("Follow user");
		mntmFollowUser.setForeground(new Color(0, 0, 0));
		mntmFollowUser.setBackground(new Color(204, 204, 255));
		mntmFollowUser.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmFollowUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnFollowUserClicked();
			}
		});
		mnNewMenu.add(mntmFollowUser);
		
		JMenuItem mntmAuditHistory = new JMenuItem("Audit history");
		mntmAuditHistory.setForeground(new Color(0, 0, 0));
		mntmAuditHistory.setBackground(new Color(204, 204, 255));
		mntmAuditHistory.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmAuditHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnAuditHistoryClicked();
			}
		});
		mnNewMenu.add(mntmAuditHistory);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBackground(Color.GRAY);
		mnNewMenu.add(separator);
		
		JMenuItem mntmLogout = new JMenuItem("Log out");
		mntmLogout.setForeground(new Color(0, 0, 0));
		mntmLogout.setBackground(new Color(204, 204, 255));
		mntmLogout.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmLogOutClicked();
			}
		});
		
		JMenuItem mntmChangePassword = new JMenuItem("Change password");
		mntmChangePassword.setForeground(new Color(0, 0, 0));
		mntmChangePassword.setBackground(new Color(204, 204, 255));
		mntmChangePassword.setPreferredSize(new Dimension(180, 24));
		mntmChangePassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmPasswordClicked();
			}
		});
		mnNewMenu.add(mntmChangePassword);
		mnNewMenu.add(mntmLogout);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnAdminLeft = new JPanel();
		pnAdminLeft.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
		contentPane.add(pnAdminLeft, BorderLayout.WEST);
		pnAdminLeft.setLayout(new BoxLayout(pnAdminLeft, BoxLayout.Y_AXIS));
		
		JPanel pnCreateUser = new JPanel();
		pnCreateUser.setBackground(new Color(204, 204, 255));
		pnAdminLeft.add(pnCreateUser);
		pnCreateUser.addMouseListener(new PanelAdminMouseClickListener(pnCreateUser));
		pnCreateUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pnCreateUserClicked();
			}
			
		});
		pnCreateUser.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel(" Manage user ");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnCreateUser.add(lblNewLabel);
		
		JPanel pnFollowUser = new JPanel();
		pnFollowUser.setBackground(new Color(204, 204, 255));
		pnAdminLeft.add(pnFollowUser);
		pnFollowUser.setLayout(new BorderLayout(0, 0));
		pnFollowUser.addMouseListener(new PanelAdminMouseClickListener(pnFollowUser));
		pnFollowUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pnFollowUserClicked();
			}
			
		});
		
		JLabel lb = new JLabel(" Follow user ");
		lb.setHorizontalTextPosition(SwingConstants.CENTER);
		lb.setHorizontalAlignment(SwingConstants.CENTER);
		lb.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnFollowUser.add(lb, BorderLayout.CENTER);
		
		JPanel pnAuditHistory = new JPanel();
		pnAuditHistory.setBackground(new Color(204, 204, 255));
		pnAdminLeft.add(pnAuditHistory);
		pnAuditHistory.setLayout(new BorderLayout(0, 0));
		pnAuditHistory.addMouseListener(new PanelAdminMouseClickListener(pnAuditHistory));
		pnAuditHistory.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pnAuditHistoryClicked();
			}
			
		});
		
		JLabel lblNewLabel_2 = new JLabel(" Audit history ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnAuditHistory.add(lblNewLabel_2, BorderLayout.CENTER);
		
		pnCardCenterAdmin = new JPanel();
		pnCardCenterAdmin.setBackground(new Color(255, 204, 204));
		contentPane.add(pnCardCenterAdmin, BorderLayout.CENTER);
		pnCardCenterAdmin.setLayout(new CardLayout(0, 0));
		
		pnCardCenterAdmin.add("PnCreateUser", new PnCreateUser());
		pnCardCenterAdmin.add("PnFollowUser", new PnFollowUser());
		pnCardCenterAdmin.add("PnAuditHistory", new PnAuditHistory());
		pnCardCenterAdmin.add("PnPassword", new PnPassword());
		
	}
	//============================================================================================================================================
	private void pnCreateUserClicked(){
		CardLayout card = (CardLayout) pnCardCenterAdmin.getLayout();
		card.show(pnCardCenterAdmin, "PnCreateUser");
	}
	
	private void pnFollowUserClicked() {
		CardLayout card = (CardLayout) pnCardCenterAdmin.getLayout();
		card.show(pnCardCenterAdmin, "PnFollowUser");
	}
	private void pnAuditHistoryClicked() {
		CardLayout card = (CardLayout) pnCardCenterAdmin.getLayout();
		card.show(pnCardCenterAdmin, "PnAuditHistory");
	}
	
	private void mntmLogOutClicked() {
		try {
			//check if audit is on and is this user followed by admin?
			if (ViewBag.isAudit && user.isFollowedByAdmin()) {
				//write logout history
				SystemServices.addAuditHistory(ViewBag.getUser(), 2);
			}
			
			MainAdmin frame = new MainAdmin();
			frame.setVisible(true);
			this.dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void mntmPasswordClicked() {
		CardLayout card = (CardLayout) pnCardCenterAdmin.getLayout();
		card.show(pnCardCenterAdmin, "PnPassword");
	}
}
