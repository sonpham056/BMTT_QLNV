package app.gui.admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import app.bus.UserBUS;
import app.bus.services.SystemServices;
import app.bus.viewbag.ViewBag;
import app.dto.User;
import app.table.JTableUnEdit;

public class PnFollowUser extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;

	public PnFollowUser() {
		setBounds(0, 0, 673, 530);
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table =  new JTable(new JTableUnEdit(new Object[] {"User", "follow?"}, 0));
		table.setRowHeight(30);
		table.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(100, 100));
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel pnButton = new JPanel();
		panel.add(pnButton);
		pnButton.setLayout(null);
		
		JButton btnFollow = new JButton("Follow/Unfollow");
		btnFollow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFollowClicked();
			}
		});
		btnFollow.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnFollow.setBounds(502, 21, 161, 49);
		pnButton.add(btnFollow);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTable();
				if (ViewBag.isAudit) {
					try {
						SystemServices.addAuditHistory(ViewBag.getUser(), 6);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRefresh.setBounds(10, 21, 161, 49);
		pnButton.add(btnRefresh);
		
		loadTable();
		
		ViewBag.tableFromPnFollowUser = this.table;
	}
	//=====================================================================================================================================================
	private void btnFollowClicked() {
		try {
			//JOptionPane.showMessageDialog(this, table.getSelectedRow());
			if (table.getSelectedRow() == -1) {
				throw new Exception("Please choose a row to change follow rule");
			}
			String name = (String) table.getModel().getValueAt(table.getSelectedRow(), 0);
			User user = checkIsUserExist(name);
			user.setFollowedByAdmin(!user.isFollowedByAdmin());
			UserBUS.update(user);
			
			table.getModel().setValueAt(user.isFollowedByAdmin(), table.getSelectedRow(), 1);
			
			//write audit history if update succeed
			if (ViewBag.isAudit) {
				//write login history
				SystemServices.addAuditHistory(ViewBag.getUser(), 4);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	//=====================================================================================================================================================
	private void loadTable() {
		try {
			JTableUnEdit model = (JTableUnEdit) table.getModel();
			model.setRowCount(0);
			
			List<User> list = UserBUS.getAll();
			for (User user : list) {
				//skip if user role is admin
				if (user.getRole().getRoleId() == 1) {
					continue;
				}
				model.addRow(new Object[] {
						user.getEmail(),
						user.isFollowedByAdmin() == true ? true : false
					});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private User checkIsUserExist(String name) throws Exception{
		return UserBUS.getByEmail(name);
	}
}
