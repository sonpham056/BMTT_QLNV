package app.gui.admin;

import java.awt.BorderLayout;
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

import app.bus.Services.MouseListener.PanelAdminMouseClickListener;
import app.dto.User;
import java.awt.CardLayout;

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
		
		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem_2);
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
		
		JLabel lblNewLabel = new JLabel("  Create user  ");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnCreateUser.add(lblNewLabel);
		
		JPanel panel_2_1 = new JPanel();
		pnAdminLeft.add(panel_2_1);
		panel_2_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2_1.add(lblNewLabel_1, BorderLayout.CENTER);
		
		JPanel panel_2_2 = new JPanel();
		pnAdminLeft.add(panel_2_2);
		panel_2_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2_2.add(lblNewLabel_2, BorderLayout.CENTER);
		
		pnCardCenterAdmin = new JPanel();
		contentPane.add(pnCardCenterAdmin, BorderLayout.CENTER);
		pnCardCenterAdmin.setLayout(new CardLayout(0, 0));
		
		pnCardCenterAdmin.add("PnCreateUser", new PnCreateUser());
	}
	
	private void pnCreateUserClicked(){
		JOptionPane.showMessageDialog(contentPane, "clicked");
	}
}
