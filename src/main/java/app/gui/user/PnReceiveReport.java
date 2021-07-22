package app.gui.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import app.bus.ReportBUS;
import app.bus.services.SystemServices;
import app.bus.viewbag.ViewBag;
import app.dto.Report;
import app.dto.User;
import app.table.JTableUnEdit;

public class PnReceiveReport extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;

	public PnReceiveReport() {
		setBackground(new Color(204, 204, 255));
		setBounds(0, 0, 674, 503);
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable(new JTableUnEdit(new Object[] { "Sender", "Title", "Send date", "IsRead?", "" }, 0));
		table.setRowHeight(30);
		table.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 204, 255));
		panel.setPreferredSize(new Dimension(10, 50));
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(null);

		JButton btnRead = new JButton("Read");
		btnRead.setContentAreaFilled(false);
		btnRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnReadClicked();
			}
		});
		btnRead.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRead.setBounds(464, 10, 200, 30);
		panel.add(btnRead);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setContentAreaFilled(false);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTable();
			}
		});
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRefresh.setBounds(10, 10, 200, 30);
		panel.add(btnRefresh);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDeleteClicked();
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelete.setContentAreaFilled(false);
		btnDelete.setBounds(220, 10, 200, 30);
		panel.add(btnDelete);

		loadTable();
	}

	// ================================================================================================================================================
	private void btnReadClicked() {
		try {
			User currentUser = ViewBag.getUser();
			//check is this user have permission to do this
			if (!currentUser.getAuthorizationTable().isReport()) {
				throw new Exception("You do not have the authority to do this!");
			}
			JTableUnEdit model = (JTableUnEdit) table.getModel();
			if (table.getSelectedRow() == -1) {
				throw new Exception("Please choose a row!");
			}
			int reportId =  (int) model.getValueAt(table.getSelectedRow(), 4);
			Report report = ReportBUS.getById(reportId);
			FrameReportContent frameReportContent = new FrameReportContent(report.getContent());
			frameReportContent.setVisible(true);
			
			if (!(boolean) model.getValueAt(table.getSelectedRow(), 3)) {
				model.setValueAt(true, table.getSelectedRow(), 3);
				report.setRead(true);
				ReportBUS.update(report);
			}
			
			if (ViewBag.isAudit && currentUser.isFollowedByAdmin()) {
				SystemServices.addAuditHistory(currentUser, 10);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void btnDeleteClicked() {
		try {
			User currentUser = ViewBag.getUser();
			//check is this user have permission to do this
			if (!currentUser.getAuthorizationTable().isReport()) {
				throw new Exception("You do not have the authority to do this!");
			}
			JTableUnEdit model = (JTableUnEdit) table.getModel();
			if (table.getSelectedRow() == -1) {
				throw new Exception("Please choose a row!");
			}
			int reportId =  (int) model.getValueAt(table.getSelectedRow(), 4);
			Report report = ReportBUS.getById(reportId);
			ReportBUS.delete(report);
			
			if (ViewBag.isAudit && currentUser.isFollowedByAdmin()) {
				SystemServices.addAuditHistory(currentUser, 12);
			}
			loadTable();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
	}

	// ================================================================================================================================================
	private void loadTable() {
		try {
			JTableUnEdit model = (JTableUnEdit) table.getModel();
			model.setRowCount(0);
			
			List<Report> list = ReportBUS.getReportsById(ViewBag.getUser().getUserId());
			for (Report r : list) {
				model.addRow(new Object[] {
						r.getSender().getEmail(),
						r.getTitle(),
						r.getSendDate(),
						r.isRead(), 
						r.getReportId()
				});
			}
			TableColumnModel tcm = table.getColumnModel();
			if (tcm.getColumnCount() == 5) {
				tcm.removeColumn(tcm.getColumn(4));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
	}
}
