package app.gui.admin;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

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
import app.bus.Services.SystemServices;
import app.dto.AuthorizationTable;
import app.dto.User;

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
	
	private ResourceBundle bundle = SystemServices.getBundle();

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
		btnCreate.setBounds(222, 458, 134, 54);
		add(btnCreate);

		JButton btnClear = new JButton("Clear");
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClear.setBounds(529, 458, 134, 54);
		add(btnClear);
		
		cbReports = new JCheckBox("Send reports");
		cbReports.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cbReports.setBounds(222, 394, 192, 48);
		add(cbReports);
		
		cbChangeInfo = new JCheckBox("Change info");
		cbChangeInfo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cbChangeInfo.setBounds(471, 394, 192, 48);
		add(cbChangeInfo);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBounds(366, 458, 134, 54);
		add(btnUpdate);
		
		JButton btnFind = new JButton("Find by email");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFindClicked();
			}
		});
		btnFind.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFind.setBounds(78, 458, 134, 54);
		add(btnFind);
	}

	//==================================================================================================================================
	private void btnCreateClicked() {
		try {
			checkIsInputEmpty();
			checkIsPasswordMatch();
			checkUserExist();
			
			String pass = new String(txtPassword.getPassword());
			Date date = getDateFromDatePicker();
			//create authorization table for user first
			AuthorizationTable author = new AuthorizationTable(cbReports.isSelected(), cbChangeInfo.isSelected());
			int authorizationTableId = AuthorizationTableBUS.add(author);
			//create user and add to db
			User user = new User(txtEmail.getText(), pass, txtName.getText(), txtLastName.getText(), date, new AuthorizationTable(authorizationTableId));
			int id = UserBUS.add(user);
			JOptionPane.showMessageDialog(this, "User added " + id);
			//if audit is on write audit history
			if (SystemServices.checkSystemAudit(bundle)) {
				//audit here if create succeed
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
				throw new Exception("Cannot found user!");
			}
			
			bindUserToView(user);
			
			//if audit is on write audit history
			if (SystemServices.checkSystemAudit(bundle)) {
				//audit here if find succeed
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	
	
	
	//==================================================================================================================================
	private void checkIsInputEmpty() throws Exception {
		if (txtEmail.getText().isBlank() || txtPassword.getPassword().toString().isBlank()
				|| txtConfirmPassword.getPassword().toString().isBlank() || txtName.getText().isBlank()
				|| txtLastName.getText().isBlank() || dpDateOfBirth.getComponentDateTextField().getText().isBlank()) {
			throw new Exception("Please provide all of the information");
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
	
	private void checkUserExist() throws Exception {
		if (UserBUS.getByEmail(txtEmail.getText()) != null) {
			throw new Exception("This user has already exist!");
		}
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
}
