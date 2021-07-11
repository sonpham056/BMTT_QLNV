package app.gui.admin;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import app.bus.AuthorizationTableBUS;
import app.bus.UserBUS;
import app.bus.services.SystemServices;
import app.bus.services.ValidateCheck;
import app.bus.viewbag.ViewBag;
import app.dto.AuthorizationTable;
import app.dto.User;
import app.gui.SHA;
import app.table.JTableUnEdit;

public class PnCreateUser extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtEmail;
	private JPasswordField txtPassword;
	private JPasswordField txtConfirmPassword;
	private JTextField txtName;
	private JTextField txtLastName;
	private DatePicker dpDateOfBirth;
	private JCheckBox cbReports;
	private JCheckBox cbChangeInfo;
	private JCheckBox cbTimeKeeping;
	


	public PnCreateUser() {
		setFont(new Font("Tahoma", Font.PLAIN, 20));
		setBounds(0, 0, 673, 530);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Email (user name)");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEADING);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 10, 202, 48);
		add(lblNewLabel);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtEmail.setBounds(222, 11, 441, 54);
		add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblLastName = new JLabel("Password");
		lblLastName.setHorizontalAlignment(SwingConstants.LEADING);
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLastName.setBounds(10, 74, 202, 54);
		add(lblLastName);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPassword.setBounds(222, 75, 441, 54);
		add(txtPassword);

		JLabel lblConfirm = new JLabel("Confirm password");
		lblConfirm.setHorizontalAlignment(SwingConstants.LEADING);
		lblConfirm.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblConfirm.setBounds(10, 138, 202, 54);
		add(lblConfirm);

		txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtConfirmPassword.setBounds(222, 139, 441, 54);
		add(txtConfirmPassword);

		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.LEADING);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(10, 202, 202, 54);
		add(lblName);

		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtName.setColumns(10);
		txtName.setBounds(222, 203, 441, 54);
		add(txtName);

		JLabel lblLastName_1 = new JLabel("Last name");
		lblLastName_1.setHorizontalAlignment(SwingConstants.LEADING);
		lblLastName_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLastName_1.setBounds(10, 266, 202, 54);
		add(lblLastName_1);

		txtLastName = new JTextField();
		txtLastName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtLastName.setColumns(10);
		txtLastName.setBounds(222, 267, 441, 54);
		add(txtLastName);

		JLabel lblDateOfBirth = new JLabel("Date of birth");
		lblDateOfBirth.setHorizontalAlignment(SwingConstants.LEADING);
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDateOfBirth.setBounds(10, 330, 202, 54);
		add(lblDateOfBirth);

		JLabel lblRole = new JLabel("Role");
		lblRole.setHorizontalAlignment(SwingConstants.LEADING);
		lblRole.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRole.setBounds(10, 394, 202, 54);
		add(lblRole);

		dpDateOfBirth = new DatePicker();
		dpDateOfBirth.getComponentDateTextField().setFont(new Font("Tahoma", Font.PLAIN, 20));
		DatePickerSettings setting = dpDateOfBirth.getSettings();
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
		dpDateOfBirth.getComponentToggleCalendarButton().setText("");
		dpDateOfBirth.setBounds(221, 331, 442, 54);
		add(dpDateOfBirth);

		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCreateClicked();
			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCreate.setBounds(241, 458, 134, 54);
		add(btnCreate);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnClearClicked();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClear.setBounds(529, 458, 134, 54);
		add(btnClear);
		
		cbReports = new JCheckBox("Reports");
		cbReports.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cbReports.setBounds(222, 394, 126, 48);
		add(cbReports);
		
		cbChangeInfo = new JCheckBox("Change info");
		cbChangeInfo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cbChangeInfo.setBounds(366, 397, 134, 48);
		add(cbChangeInfo);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUpdateClicked();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBounds(385, 458, 134, 54);
		add(btnUpdate);
		
		JButton btnFind = new JButton("Find by email");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFindClicked();
			}
		});
		btnFind.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFind.setBounds(97, 458, 134, 54);
		add(btnFind);
		
		JButton btnDel = new JButton("Del");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDelClicked();
			}
		});
		btnDel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDel.setBounds(10, 458, 77, 54);
		add(btnDel);
		
		cbTimeKeeping = new JCheckBox("TimeKeeping");
		cbTimeKeeping.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbTimeKeeping.setBounds(529, 397, 134, 48);
		add(cbTimeKeeping);
	}

	//==================================================================================================================================
	private void btnCreateClicked() {
		try {
			checkIsInputEmpty();
			checkValidInfo();
			checkIsPasswordMatch();
			if (checkIsUserExist() != null) {
				throw new Exception("This user has already exist");
			}
			
			String email = txtEmail.getText();
			String pass = new String(txtPassword.getPassword());
			
			Date date = getDateFromDatePicker();
			//create authorization table for user first
			AuthorizationTable author = new AuthorizationTable(cbReports.isSelected(), cbChangeInfo.isSelected(), cbTimeKeeping.isSelected());
			int authorizationTableId = AuthorizationTableBUS.add(author);
			//create user and add to db
			String sha = SHA.toHexString(email, pass);
			User user = new User(txtEmail.getText(), sha, txtName.getText(), txtLastName.getText(), date, new AuthorizationTable(authorizationTableId));
			int id = UserBUS.add(user);
			JOptionPane.showMessageDialog(this, "User added " + id);
			
			//add row to table of follow user
			addUserToFollowUserPanelTable(user);
			//if audit is on write audit history
			if (ViewBag.isAudit) {
				//audit here if create succeed
				SystemServices.addAuditHistory(ViewBag.getUser(), 3);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	private void btnFindClicked() {
		try {
			checkIsEmailEmpty();
			
			User user = UserBUS.getByEmail(txtEmail.getText());
			if (user == null) {
				throw new Exception("User not found!");
			}
			
			bindUserToView(user);
			
			//if audit is on write audit history
			if (ViewBag.isAudit) {
				//audit here if find succeed
				SystemServices.addAuditHistory(ViewBag.getUser(), 6);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	private void btnUpdateClicked() {
		try {
			checkIsInputEmptyForUpdate();
			checkValidInfo();
			User user = checkIsUserExist();
			if (user == null) {
				throw new Exception("This user doesn't exist");
			}
			
			//update author table
			AuthorizationTable author = user.getAuthorizationTable();
			author.setReport(cbReports.isSelected());
			author.setUserInfo(cbChangeInfo.isSelected());
			author.setTimeKeeping(cbTimeKeeping.isSelected());
			AuthorizationTableBUS.update(author);
			
			//update student
			Date date = getDateFromDatePicker();
			user.setEmail(txtEmail.getText());
			user.setName(txtName.getText());
			user.setLastName(txtLastName.getText());
			user.setDateOfBirth(date);
			UserBUS.update(user);
			
			//if audit is on write audit history
			if (ViewBag.isAudit) {
				//audit here if update succeed
				SystemServices.addAuditHistory(ViewBag.getUser(), 4);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void btnDelClicked() {
		try {
			checkIsEmailEmpty();
			checkValidInfo();
			User user = checkIsUserExist();
			if (user == null) {
				throw new Exception("This user doesn't exist");
			}
			if (JOptionPane.showConfirmDialog(this, "Do you want to delete this user?", "Are you sure?", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
				//remove user from follow user table
				removeUserFromPanelFollowTable(user);
				UserBUS.delete(user);
				AuthorizationTableBUS.remove(user.getAuthorizationTable());
				JOptionPane.showMessageDialog(this, "User deleted");
				btnClearClicked();
			}
			
			//if audit is on write audit history
			if (ViewBag.isAudit) {
				//audit here if delete succeed
				SystemServices.addAuditHistory(ViewBag.getUser(), 5);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void btnClearClicked() {
		txtEmail.setText("");
		txtName.setText("");
		txtLastName.setText("");
		txtPassword.setText("");
		txtConfirmPassword.setText("");
		cbChangeInfo.setSelected(false);
		cbReports.setSelected(false);
		dpDateOfBirth.getComponentDateTextField().setText("");
	}
	//==================================================================================================================================
	private void checkIsInputEmpty() throws Exception {
		if (txtEmail.getText().isBlank() || txtPassword.getPassword().toString().isBlank()
				|| txtConfirmPassword.getPassword().toString().isBlank() || txtName.getText().isBlank()
				|| txtLastName.getText().isBlank() || dpDateOfBirth.getComponentDateTextField().getText().isBlank()) {
			throw new Exception("Please provide all of the information");
		}
	}
	private void checkIsInputEmptyForUpdate() throws Exception {
		if (txtEmail.getText().isBlank() ||  txtName.getText().isBlank()|| txtLastName.getText().isBlank() 
				|| dpDateOfBirth.getComponentDateTextField().getText().isBlank()) {
			throw new Exception("Please provide all of the information (Except password)");
		}
	}

	private void checkIsPasswordMatch() throws Exception {
		String pass = new String(txtPassword.getPassword());
		String confirm = new String(txtConfirmPassword.getPassword());
		if (pass.compareTo(confirm) != 0) {
			throw new Exception("Password missed match");
		}
	}

	private Date getDateFromDatePicker() throws Exception {
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		date = formatter.parse(dpDateOfBirth.getComponentDateTextField().getText());
		if (date == null) {
			throw new Exception("Cannot convert date");
		}
		return date;
	}
	
	private User checkIsUserExist() throws Exception{
		return UserBUS.getByEmail(txtEmail.getText());
	}
	
	private void checkIsEmailEmpty() throws Exception {
		if (txtEmail.getText().isBlank()) {
			throw new Exception("Please fill the email text");
		}
	}
	
	private void bindUserToView(User user) {
		txtLastName.setText(user.getLastName());
		txtName.setText(user.getName());
		dpDateOfBirth.getComponentDateTextField().setText(formatDate(user.getDateOfBirth()));
		cbReports.setSelected(user.getAuthorizationTable().isReport());
		cbChangeInfo.setSelected(user.getAuthorizationTable().isUserInfo());
	}
	
	private String formatDate(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(date);
	}
	
	private void addUserToFollowUserPanelTable(User user) {
		JTableUnEdit model = (JTableUnEdit) ViewBag.tableFromPnFollowUser.getModel();
		model.addRow(new Object[] {
				user.getEmail(),
				user.isFollowedByAdmin()
		});
	}
	
	private void removeUserFromPanelFollowTable(User user) {
		try {
			JTableUnEdit model = (JTableUnEdit) ViewBag.tableFromPnFollowUser.getModel();
			int row = -1;
			for (int i = 0; i < model.getRowCount(); i++) {
				String name = (String) model.getValueAt(i, 0);
				if (name.equals(user.getEmail())){
					row = i;
					break;
				}
			}
			model.removeRow(row);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	private void checkValidInfo() throws Exception {
		ValidateCheck.checkEmail(txtEmail.getText());
		ValidateCheck.checkDateValid(dpDateOfBirth.getComponentDateTextField().getText());
	}
}
