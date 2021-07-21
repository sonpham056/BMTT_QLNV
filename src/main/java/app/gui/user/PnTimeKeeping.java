package app.gui.user;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import app.bus.TimeKeepingBUS;
import app.bus.services.SystemServices;
import app.bus.viewbag.ViewBag;
import app.dto.TimeKeeping;
import app.dto.User;
import app.table.JTableUnEdit;
import java.awt.Color;

public class PnTimeKeeping extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;

	public PnTimeKeeping() {
		setBackground(new Color(204, 204, 255));
		setBounds(0, 0, 674, 503);
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable(new JTableUnEdit(new Object[] { "User", "Start Time", "End time" }, 0));
		table.setRowHeight(30);
		table.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 204, 255));
		panel.setPreferredSize(new Dimension(10, 50));
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(null);

		JButton btnStart = new JButton("Start");
		btnStart.setContentAreaFilled(false);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStartClicked();
			}
		});
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnStart.setBounds(10, 10, 148, 30);
		panel.add(btnStart);

		JButton btnEnd = new JButton("End");
		btnEnd.setContentAreaFilled(false);
		btnEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEndClicked();
			}
		});
		btnEnd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEnd.setBounds(516, 10, 148, 30);
		panel.add(btnEnd);

		loadTable();
	}

	// ========================================================================================================================================
	private void btnStartClicked() {
		try {
			checkTableRowIfEndTimeIsNull();
			User currentUser = ViewBag.getUser();
			//check is this user have permission to do this
			if (!currentUser.getAuthorizationTable().isTimeKeeping()) {
				throw new Exception("You do not have the authority to do this!");
			}
			TimeKeeping time = new TimeKeeping();
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			time.setStartTime(df.parse(df.format(new Date())));
			time.setUser(currentUser);
			TimeKeepingBUS.addStartTime(time);

			if (ViewBag.isAudit && currentUser.isFollowedByAdmin()) {
				SystemServices.addAuditHistory(currentUser, 7);
			}
			loadTable();
			// JOptionPane.showMessageDialog(this, df.format(time.getStartTime()));
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	private void btnEndClicked() {
		try {
			checkTableRowIfEndTimeIsNotNull();
			User currentUser = ViewBag.getUser();
			//check is this user have permission to do this
			if (!currentUser.getAuthorizationTable().isTimeKeeping()) {
				throw new Exception("You do not have the authority to do this!");
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			TimeKeeping time = TimeKeepingBUS.getLatestTimeKeeping(currentUser.getEmail());
			time.setEndTime(df.parse(df.format(new Date())));
			
			TimeKeepingBUS.updateEndTime(time);
			
			if (ViewBag.isAudit && currentUser.isFollowedByAdmin()) {
				SystemServices.addAuditHistory(currentUser, 8);
			}
			loadTable();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	// ========================================================================================================================================

	private void loadTable() {
		try {
			JTableUnEdit model = (JTableUnEdit) table.getModel();
			model.setRowCount(0);
			User user = ViewBag.getUser();
			List<TimeKeeping> list = TimeKeepingBUS.getById(user.getUserId());
			for (TimeKeeping t : list) {
				model.addRow(new Object[] { user.getEmail(), t.getStartTime(),
						t.getEndTime() == null ? "" : t.getEndTime() });
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	private void checkTableRowIfEndTimeIsNull() throws Exception {
		JTableUnEdit model = (JTableUnEdit) table.getModel();
		if (model.getRowCount() > 0) {
			String column3 = (String) model.getValueAt(0, 2).toString();
			if (column3.isBlank()) {
				throw new Exception("You must end your timekeeping before staring a new one!");
			}
		}
	}
	
	private void checkTableRowIfEndTimeIsNotNull() throws Exception {
		JTableUnEdit model = (JTableUnEdit) table.getModel();
		if (model.getRowCount() > 0) {
			String column3 = (String) model.getValueAt(0, 2).toString();
			if (!column3.isBlank()) {
				throw new Exception("You must start your timekeeping before ending!");
			}
		} else {
			throw new Exception("Nothing here to end! stop testing the app!");
		}
	}
}
