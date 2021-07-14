package app.gui.user;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import app.bus.UserBUS;
import app.bus.services.SystemServices;
import app.bus.services.ValidateCheck;
import app.bus.viewbag.ViewBag;
import app.dto.User;

public class PnInfo extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtEmail;
	private JTextField txtName;
	private JTextField txtLastName;
	private DatePicker dpDateOfBirth;
	
	public PnInfo() {
		setBounds(0, 0, 674, 503);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Email (user name)");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEADING);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 10, 202, 48);
		add(lblNewLabel);
		
		txtEmail = new JTextField();
		txtEmail.setEnabled(false);
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtEmail.setColumns(10);
		txtEmail.setBounds(222, 11, 441, 54);
		add(txtEmail);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.LEADING);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(10, 75, 202, 54);
		add(lblName);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtName.setColumns(10);
		txtName.setBounds(222, 76, 441, 54);
		add(txtName);
		
		JLabel lblLastName_1 = new JLabel("Last name");
		lblLastName_1.setHorizontalAlignment(SwingConstants.LEADING);
		lblLastName_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLastName_1.setBounds(10, 139, 202, 54);
		add(lblLastName_1);
		
		txtLastName = new JTextField();
		txtLastName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtLastName.setColumns(10);
		txtLastName.setBounds(222, 140, 441, 54);
		add(txtLastName);
		
		JLabel lblDateOfBirth = new JLabel("Date of birth");
		lblDateOfBirth.setHorizontalAlignment(SwingConstants.LEADING);
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDateOfBirth.setBounds(10, 203, 202, 54);
		add(lblDateOfBirth);
		
		dpDateOfBirth = new DatePicker();
		dpDateOfBirth.getComponentToggleCalendarButton().setText("");
		dpDateOfBirth.getComponentDateTextField().setFont(new Font("Tahoma", Font.PLAIN, 20));
		dpDateOfBirth.setBounds(221, 204, 442, 54);
		add(dpDateOfBirth);
		
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
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUpdateClicked();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnUpdate.setBounds(524, 430, 139, 63);
		add(btnUpdate);
		
		bindUserToView();
	}
	//=======================================================================================================================================================
	private void btnUpdateClicked(){
		try {
			checkIsInputEmpty();
			checkValidInfo();
			User currentUser = ViewBag.getUser();
			//check is this user have permission to do this
			if (!currentUser.getAuthorizationTable().isUserInfo()) {
				throw new Exception("You do not have the authority to do this!");
			}
			User user = checkIsUserExist();
			if (user != null && !user.getEmail().equals(txtEmail.getText())) {
				throw new Exception("This email is used by other user!");
			}
			if (JOptionPane.showConfirmDialog(this, "Do you want to update your infomation?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				setUpdateUser(user);
				UserBUS.update(user);
				
				if (ViewBag.isAudit && currentUser.isFollowedByAdmin()) {
					SystemServices.addAuditHistory(currentUser, 4);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}
	//=======================================================================================================================================================
	private void checkValidInfo() throws Exception {
		ValidateCheck.checkEmail(txtEmail.getText());
		ValidateCheck.checkDateValid(dpDateOfBirth.getComponentDateTextField().getText());
		ValidateCheck.checkAgeValid(dpDateOfBirth);
	}
	
	private User checkIsUserExist() throws Exception{
		return UserBUS.getByEmail(txtEmail.getText());
	}
	
	private void checkIsInputEmpty() throws Exception {
		if (txtEmail.getText().isBlank() || txtName.getText().isBlank()	|| txtLastName.getText().isBlank() 
				|| dpDateOfBirth.getComponentDateTextField().getText().isBlank()) {
			throw new Exception("Please provide all of the information");
		}
	}
	
	private void setUpdateUser(User user) throws ParseException {
		user.setEmail(txtEmail.getText());
		user.setName(txtName.getText());
		user.setLastName(txtLastName.getText());
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		user.setDateOfBirth(df.parse(dpDateOfBirth.getComponentDateTextField().getText()));
	}
	
	private void bindUserToView() {
		User user = ViewBag.getUser();
		txtEmail.setText(user.getEmail());
		txtLastName.setText(user.getLastName());
		txtName.setText(user.getName());
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		dpDateOfBirth.getComponentDateTextField().setText(df.format(user.getDateOfBirth()));
	}
}
