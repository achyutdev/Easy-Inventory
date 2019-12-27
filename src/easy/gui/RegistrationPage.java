package easy.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class RegistrationPage extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField usernametextField;
	private JPasswordField pwdPassword;
	private JTextField nameTextField;
	private JTextField emailTextField;
	private JTextField addressTextField;
	private JTextField phonenumTextField;
	private JTextField titletextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrationPage dialog = new RegistrationPage();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	public void clearFieldOnClick(String fieldName) {
		switch (fieldName) {
		case "username":
			usernametextField.setText("");
			System.out.println("username");
			break;
		case "address":
			addressTextField.setText("");
			break;
		case "email":
			emailTextField.setText("");
			break;
		case "phonenum":
			phonenumTextField.setText("");
			break;
		case "title":
			titletextField.setText("");
			break;
		case "name":
			nameTextField.setText("");
			break;
		default:
			break;
		}
	}

	public RegistrationPage() {
		setTitle("Registration ");
		setBounds(100, 100, 450, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.WEST);

		JLabel lbllogo = new JLabel("[Logo]");

		JLabel lblRegistorNewUser = new JLabel("Registor New User Account");
		lblRegistorNewUser.setForeground(SystemColor.textHighlight);
		lblRegistorNewUser.setFont(new Font("Segoe Script", Font.BOLD, 20));

		JLabel lblUsername = new JLabel("Username");

		JLabel lblPassword = new JLabel("Password");

		JLabel lblName = new JLabel("Name");

		JLabel lblEmail = new JLabel("Email");

		JLabel lblAddress = new JLabel("Address");

		JLabel lblPhone = new JLabel("Phone");

		JLabel lblTitle = new JLabel("Title");

		
		usernametextField = new JTextField();
		usernametextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clearFieldOnClick("username");
			}
		});
		usernametextField.setForeground(SystemColor.activeCaptionBorder);
		usernametextField.setText("e.g. username");
		usernametextField.setColumns(10);

		pwdPassword = new JPasswordField();

		nameTextField = new JTextField();
		nameTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clearFieldOnClick("name");
			}
		});
		nameTextField.setForeground(Color.LIGHT_GRAY);
		nameTextField.setText("e.g. Achyut Dev");
		nameTextField.setColumns(10);

		emailTextField = new JTextField();
		emailTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clearFieldOnClick("email");
			}
		});
		emailTextField.setForeground(Color.LIGHT_GRAY);
		emailTextField.setText("e.g. achyut.dev@gmail.com");
		emailTextField.setColumns(10);

		addressTextField = new JTextField();
		addressTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clearFieldOnClick("address");
			}
		});
		addressTextField.setForeground(Color.LIGHT_GRAY);
		addressTextField.setText("e.g. Fairfield, IA");
		addressTextField.setColumns(10);

		phonenumTextField = new JTextField();
		phonenumTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clearFieldOnClick("phonenum");
			}
		});
		phonenumTextField.setForeground(Color.LIGHT_GRAY);
		phonenumTextField.setText("e.g. 4323234423");
		phonenumTextField.setColumns(10);

		titletextField = new JTextField();
		titletextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clearFieldOnClick("title");
			}
		});
		titletextField.setForeground(Color.LIGHT_GRAY);
		titletextField.setText("e.g. Sale Manager");
		titletextField.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(31)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addComponent(lblName)
								.addComponent(lblEmail).addComponent(lblAddress).addComponent(lblPhone)
								.addComponent(lblTitle)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
										.addGroup(Alignment.LEADING,
												gl_contentPanel.createSequentialGroup().addComponent(lbllogo).addGap(18)
														.addComponent(lblRegistorNewUser))
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(lblUsername).addComponent(lblPassword))
										.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
												.addComponent(pwdPassword)
												.addComponent(usernametextField, GroupLayout.DEFAULT_SIZE, 241,
														Short.MAX_VALUE)
												.addComponent(emailTextField).addComponent(nameTextField)
												.addComponent(addressTextField).addComponent(phonenumTextField)
												.addComponent(titletextField)))))
						.addGap(57)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lbllogo)
								.addComponent(lblRegistorNewUser))
				.addGap(18)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblUsername)
						.addComponent(usernametextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblPassword)
						.addComponent(pwdPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblName).addComponent(
						nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblEmail).addComponent(
						emailTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblAddress).addComponent(
						addressTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblPhone).addComponent(
						phonenumTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblTitle).addComponent(
						titletextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)).addContainerGap(49, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
}
