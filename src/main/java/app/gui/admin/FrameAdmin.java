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
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import app.bus.services.listenerclass.PanelAdminMouseClickListener;
import app.dto.User;
import app.gui.Main;

import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setResizable(false);
		this.user = user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Go to");
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmManageUser = new JMenuItem("Manage User");
		mntmManageUser.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmManageUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnCreateUserClicked();
			}
		});
		mnNewMenu.add(mntmManageUser);
		
		JMenuItem mntmFollowUser = new JMenuItem("Follow user");
		mntmFollowUser.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmFollowUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnFollowUserClicked();
			}
		});
		mnNewMenu.add(mntmFollowUser);
		
		JMenuItem mntmAuditHistory = new JMenuItem("Audit history");
		mntmAuditHistory.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmAuditHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnAuditHistoryClicked();
			}
		});
		mnNewMenu.add(mntmAuditHistory);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem mntmLogout = new JMenuItem("Log out");
		mntmLogout.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmLogOutClicked();
			}
		});
		mnNewMenu.add(mntmLogout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnAdminLeft = new JPanel();
		pnAdminLeft.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
		contentPane.add(pnAdminLeft, BorderLayout.WEST);
		pnAdminLeft.setLayout(new BoxLayout(pnAdminLeft, BoxLayout.Y_AXIS));
		
		JPanel pnCreateUser = new JPanel();
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
		contentPane.add(pnCardCenterAdmin, BorderLayout.CENTER);
		pnCardCenterAdmin.setLayout(new CardLayout(0, 0));
		
		pnCardCenterAdmin.add("PnCreateUser", new PnCreateUser());
		pnCardCenterAdmin.add("PnFollowUser", new PnFollowUser());
		pnCardCenterAdmin.add("PnAuditHistory", new PnAuditHistory());
		
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
		Main frame = new Main();
		frame.setVisible(true);
		this.dispose();
	}
}
