package app.gui.admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import app.bus.UserBUS;
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
//		DefaultTableModel model = (DefaultTableModel) table.getModel();
//		model.addRow(new Object[] {
//				"1",
//				"2",
//			});
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(100, 100));
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel pnButton = new JPanel();
		panel.add(pnButton);
		pnButton.setLayout(null);
		
		JButton btnNewButton = new JButton("Follow/Unfollow");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(502, 21, 161, 49);
		pnButton.add(btnNewButton);
		
		loadTable();
	}
	
	//=====================================================================================================================================================
	private void loadTable() {
		try {
			JTableUnEdit model = (JTableUnEdit) table.getModel();
			model.setRowCount(0);
			
			List<User> list = UserBUS.getAll();
			for (User user : list) {
				model.addRow(new Object[] {
						user.getEmail(),
						user.isFollowedByAdmin() == true ? true : false
					});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
