package app.gui.admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import app.bus.AuditHistoryBUS;
import app.bus.services.SystemServices;
import app.bus.services.listenerclass.TextFieldKeyListener;
import app.bus.viewbag.ViewBag;
import app.dto.AuditHistory;
import app.table.JTableUnEdit;
import java.awt.Color;

public class PnAuditHistoryUser extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtFilter;
	private DatePicker dpFrom;
	private DatePicker dpTo;


	public PnAuditHistoryUser() {
		setBackground(new Color(255, 204, 204));
		setBounds(0, 0, 674, 503);
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnMain = new JPanel();
		add(pnMain, BorderLayout.CENTER);
		pnMain.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pnMain.add(scrollPane, BorderLayout.CENTER);
		
		table =  new JTable(new JTableUnEdit(new Object[] {"User", "Type", "Time"}, 0));
		table.setRowHeight(30);
		table.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPane.setViewportView(table);
		
		JPanel pnTop = new JPanel();
		pnTop.setBackground(new Color(255, 204, 204));
		FlowLayout flowLayout = (FlowLayout) pnTop.getLayout();
		flowLayout.setHgap(50);
		add(pnTop, BorderLayout.NORTH);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setContentAreaFilled(false);
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
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnTop.add(btnRefresh);
		
		JPanel pnBottom = new JPanel();
		pnBottom.setBackground(new Color(255, 204, 204));
		pnBottom.setPreferredSize(new Dimension(100, 50));
		add(pnBottom, BorderLayout.SOUTH);
		pnBottom.setLayout(null);
		
		dpFrom = new DatePicker();
		dpFrom.setBounds(10, 8, 155, 32);
		dpFrom.getComponentDateTextField().setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnBottom.add(dpFrom);
		DatePickerSettings setting = dpFrom.getSettings();
		setting.setFontCalendarDateLabels(new Font("Tahoma", Font.PLAIN, 20));
		setting.setFontCalendarWeekdayLabels(new Font("Tahoma", Font.PLAIN, 20));
		setting.setFontCalendarWeekNumberLabels(new Font("Tahoma", Font.PLAIN, 20));
		setting.setFontClearLabel(new Font("Tahoma", Font.PLAIN, 20));
		setting.setFontInvalidDate(new Font("Tahoma", Font.PLAIN, 20));
		setting.setFontMonthAndYearMenuLabels(new Font("Tahoma", Font.PLAIN, 20));
		setting.setFontMonthAndYearNavigationButtons(new Font("Tahoma", Font.PLAIN, 20));
		setting.setFontTodayLabel(new Font("Tahoma", Font.PLAIN, 20));
		setting.setFontValidDate(new Font("Tahoma", Font.PLAIN, 20));
		setting.setFontVetoedDate(new Font("Tahoma", Font.PLAIN, 20));

		setting.setFormatForDatesCommonEra("dd/MM/yyyy");
		
		JLabel lblNewLabel = new JLabel(" ~ ");
		lblNewLabel.setBounds(175, 11, 27, 25);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnBottom.add(lblNewLabel);
		
		dpTo= new DatePicker();
		dpTo.getComponentDateTextField().setFont(new Font("Tahoma", Font.PLAIN, 20));
		dpTo.setBounds(212, 8, 155, 32);
		pnBottom.add(dpTo);
		
		JButton btnFind = new JButton("Find");
		btnFind.setContentAreaFilled(false);
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFindClicked();
			}
		});
		btnFind.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnFind.setBounds(377, 8, 98, 32);
		pnBottom.add(btnFind);
		
		txtFilter = new JTextField();
		txtFilter.addKeyListener(new TextFieldKeyListener(txtFilter, table));
		txtFilter.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				txtFilterFocusLost();
			}
			@Override
			public void focusGained(FocusEvent e) {
				txtFilterFocusGained();
			}
		});
		txtFilter.setText("Filter then enter");
		txtFilter.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtFilter.setBounds(485, 10, 179, 30);
		pnBottom.add(txtFilter);
		txtFilter.setColumns(10);
		setting = dpTo.getSettings();
		setting.setFontCalendarDateLabels(new Font("Tahoma", Font.PLAIN, 20));
		setting.setFontCalendarWeekdayLabels(new Font("Tahoma", Font.PLAIN, 20));
		setting.setFontCalendarWeekNumberLabels(new Font("Tahoma", Font.PLAIN, 20));
		setting.setFontClearLabel(new Font("Tahoma", Font.PLAIN, 20));
		setting.setFontInvalidDate(new Font("Tahoma", Font.PLAIN, 20));
		setting.setFontMonthAndYearMenuLabels(new Font("Tahoma", Font.PLAIN, 20));
		setting.setFontMonthAndYearNavigationButtons(new Font("Tahoma", Font.PLAIN, 20));
		setting.setFontTodayLabel(new Font("Tahoma", Font.PLAIN, 20));
		setting.setFontValidDate(new Font("Tahoma", Font.PLAIN, 20));
		setting.setFontVetoedDate(new Font("Tahoma", Font.PLAIN, 20));
		setting.setFormatForDatesCommonEra("dd/MM/yyyy");
		
		loadTable();
	}
	
	private void btnFindClicked() {
		try {
			checkDp();
			reloadTableFilter();
			loadTableWithDates();
			
			if (ViewBag.isAudit) {
				SystemServices.addAuditHistory(ViewBag.getUser(), 6);
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
	}
	//=========================================================================================================================================
	private void txtFilterFocusLost() {
		if (txtFilter.getText().isBlank()) {
			txtFilter.setText("Filter then enter");
		}
	}
	
	private void txtFilterFocusGained() {
		if (txtFilter.getText().compareTo("Filter then enter") == 0){
			txtFilter.setText("");
		}
	}
	
	private void loadTable() {
		try {
			JTableUnEdit model = (JTableUnEdit) table.getModel();
			model.setRowCount(0);
			
			List<AuditHistory> list = AuditHistoryBUS.getAuditHistory("user");
			for (AuditHistory auditHistory : list) {
				model.addRow(new Object[] {
						auditHistory.getUser().getEmail(),
						auditHistory.getAudit().getType(),
						auditHistory.getAuditTime()
					});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void loadTableWithDates() {
		try {
			JTableUnEdit model = (JTableUnEdit) table.getModel();
			model.setRowCount(0);
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date from = df.parse(dpFrom.getComponentDateTextField().getText() + " 00:00:00");
			Date to = df.parse(dpTo.getComponentDateTextField().getText() + " 23:59:59");
			List<AuditHistory> list = AuditHistoryBUS.getAuditHistoryWithDates(from, to, "user");
			for (AuditHistory auditHistory : list) {
				model.addRow(new Object[] {
						auditHistory.getUser().getEmail(),
						auditHistory.getAudit().getType(),
						auditHistory.getAuditTime()
					});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	private void checkDp() throws Exception {
		if (dpFrom.getComponentDateTextField().getText().isBlank() || dpTo.getComponentDateTextField().getText().isBlank()) {
			throw new Exception("please choose dates!");
		}

		if (dpFrom.getComponentDateTextField().getText().compareTo(dpTo.getComponentDateTextField().getText()) > 0) {
			throw new Exception("From date is bigger than To date");
		}
	}
	
	private void reloadTableFilter() {
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((JTableUnEdit) table.getModel())); 
	    sorter.setRowFilter(RowFilter.regexFilter(""));

	    table.setRowSorter(sorter);
	    txtFilter.setText("Filter then enter");
	}
}
