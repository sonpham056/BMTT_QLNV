package app.gui.user;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import app.bus.services.SystemServices;
import app.bus.services.listenerclass.PanelAdminMouseClickListener;
import app.bus.viewbag.ViewBag;
import app.dto.User;
import app.gui.MainUser;
import app.gui.PnPassword;

import java.awt.Dimension;

public class FrameUser extends JFrame {
	private static final long serialVersionUID = 1L;
	//JPanel
	private JPanel contentPane;
	private JPanel pnCardCenterUser;
	private JPanel pnInfo;
	
	
	
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public FrameUser(User user) {
		setResizable(false);
		this.user = user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Go to");
		mnNewMenu.setPreferredSize(new Dimension(100, 24));
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmTimeKeeping = new JMenuItem("Time Keeping ");
		mntmTimeKeeping.setPreferredSize(new Dimension(200, 35));
		mntmTimeKeeping.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmTimeKeeping.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnTimeKeepingClicked();
			}
		});
		mnNewMenu.add(mntmTimeKeeping);
		
		JMenuItem mntmReport = new JMenuItem("Report");
		mntmReport.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnSendReportClicked();
			}
		});
		mnNewMenu.add(mntmReport);
		
		JMenuItem mntmInfo = new JMenuItem("Information");
		mntmInfo.setPreferredSize(new Dimension(111, 35));
		mntmInfo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnNewMenu.add(mntmInfo);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem mntmLogout = new JMenuItem("Log out");
		mntmLogout.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmLogOutClicked();
			}
		});
		
		JMenuItem mntmPassword = new JMenuItem("Change password");
		mntmPassword.setPreferredSize(new Dimension(180, 24));
		mntmPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmPasswordClicked();
			}
		});
		mnNewMenu.add(mntmPassword);
		mnNewMenu.add(mntmLogout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnUserLeft = new JPanel();
		pnUserLeft.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
		contentPane.add(pnUserLeft, BorderLayout.WEST);
		pnUserLeft.setLayout(new BoxLayout(pnUserLeft, BoxLayout.Y_AXIS));
		
		JPanel pnTimeKeeping = new JPanel();
		pnUserLeft.add(pnTimeKeeping);
		pnTimeKeeping.addMouseListener(new PanelAdminMouseClickListener(pnTimeKeeping));
		pnTimeKeeping.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pnTimeKeepingClicked();
			}
			
		});
		pnTimeKeeping.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel(" TimeKeeping ");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnTimeKeeping.add(lblNewLabel);
		
		JPanel pnSendReport = new JPanel();
		pnUserLeft.add(pnSendReport);
		pnSendReport.setLayout(new BorderLayout(0, 0));
		pnSendReport.addMouseListener(new PanelAdminMouseClickListener(pnSendReport));
		pnSendReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pnSendReportClicked();
			}
			
		});
		
		JLabel lb = new JLabel(" Reports ");
		lb.setHorizontalTextPosition(SwingConstants.CENTER);
		lb.setHorizontalAlignment(SwingConstants.CENTER);
		lb.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnSendReport.add(lb, BorderLayout.CENTER);
		
		pnInfo = new JPanel();
		pnUserLeft.add(pnInfo);
		pnInfo.addMouseListener(new PanelAdminMouseClickListener(pnInfo));
		pnInfo.setLayout(new BorderLayout(0, 0));
		pnInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pnInfoClicked();
			}
			
		});
		
		JLabel lblNewLabel_1 = new JLabel("Infomation");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnInfo.add(lblNewLabel_1, BorderLayout.CENTER);
		
		pnCardCenterUser = new JPanel();
		contentPane.add(pnCardCenterUser, BorderLayout.CENTER);
		pnCardCenterUser.setLayout(new CardLayout(0, 0));
		
		pnCardCenterUser.add("PnTimeKeeping", new PnTimeKeeping());
		pnCardCenterUser.add("PnReport", new PnReport());
		pnCardCenterUser.add("PnInfo", new PnInfo());
		pnCardCenterUser.add("PnPassword", new PnPassword());
		
		
	}
	//============================================================================================================================================
	private void pnTimeKeepingClicked(){
		CardLayout card = (CardLayout) pnCardCenterUser.getLayout();
		card.show(pnCardCenterUser, "PnTimeKeeping");
	}
	
	private void pnSendReportClicked() {
		CardLayout card = (CardLayout) pnCardCenterUser.getLayout();
		card.show(pnCardCenterUser, "PnReport");
	}
	
	private void pnInfoClicked() {
		CardLayout card = (CardLayout) pnCardCenterUser.getLayout();
		card.show(pnCardCenterUser, "PnInfo");
	}
	
	private void mntmLogOutClicked(){
		try {
			//check if audit is on and is this user followed by admin?
			if (ViewBag.isAudit && user.isFollowedByAdmin()) {
				//write logout history
				SystemServices.addAuditHistory(ViewBag.getUser(), 2);
			}
			
			MainUser frame = new MainUser();
			frame.setVisible(true);
			this.dispose();
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void mntmPasswordClicked() {
		CardLayout card = (CardLayout) pnCardCenterUser.getLayout();
		card.show(pnCardCenterUser, "PnPassword");
	}
}
