package app.gui.admin;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import app.bus.UserBUS;
import app.dto.User;

public class PnCreateUser extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtEmail;
	private JPasswordField txtPassword;
	private JPasswordField txtConfirmPassword;
	private JTextField txtName;
	private JTextField txtLastName;
	private JTextField txtUser;
	private DatePicker dpDateOfBirth;

	public PnCreateUser() {
		setFont(new Font("Tahoma", Font.PLAIN, 20));
		setBounds(0, 0, 673, 530);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Email (user name)");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEADING);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 10, 202, 54);
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

		txtUser = new JTextField();
		txtUser.setText("User");
		txtUser.setEnabled(false);
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtUser.setColumns(10);
		txtUser.setBounds(222, 394, 441, 54);
		add(txtUser);

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
		btnCreate.setBounds(385, 458, 134, 54);
		add(btnCreate);

		JButton btnClear = new JButton("Clear");
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClear.setBounds(529, 458, 134, 54);
		add(btnClear);
	}

	private void btnCreateClicked() {
		try {
			checkIsInputEmpty();
			checkIsPasswordMatch();
			String pass = new String(txtPassword.getPassword());
			Date date = getDateFromDatePicker();
			User user = new User(txtEmail.getText(), pass, txtName.getText(), txtLastName.getText(), date);
			int id = UserBUS.add(user);
			JOptionPane.showMessageDialog(this, "User added" + id);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

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
}
