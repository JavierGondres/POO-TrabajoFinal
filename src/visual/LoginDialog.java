package visual;

import visual.utils.*;
import visual.utils.Animations.AnimationCallback;

import javax.swing.*;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;

import backend.classes.*;
import backend.controller.HospitalController;
import backend.utils.IdGenerator;

import javax.swing.event.ChangeEvent;


public class LoginDialog extends JFrame implements AnimationCallback {

	private static final long serialVersionUID = 1L;
	private final JLayeredPane layeredPaneB = new JLayeredPane();
	private JLabel WelcomeMsg;
	private JLabel loginLabel;
	private JLabel RegisterMsg;
	private JLabel registerLabel;
	private JLabel loginToggle;
	private JLabel lblBtn_R;
	private JLabel lblBtn_L;
	private boolean isSelected = false;
	private JPasswordField passwordField;
	private JLabel BUTTON;
	private JTextField usernameField;
	private JLabel TITLE;
	private JLabel TITLE_R;
	private JLabel Username;
	private JLabel Password;
	private JLabel Lastname;
	private JTextField lastnameField;
	private JComboBox<String> cbxMonth;
	private JSpinner yearSpinner;
	private JSpinner daySpinner;
	private JLabel Birthdate;
	private JLabel Gender;
	private JRadioButton maleBtn;
	private JRadioButton femaleBtn;
	private JLabel BUTTON_LABEL;
	private JLabel REGISTER_LABEL;
	private JLayeredPane layeredPane;
	private JLayeredPane layeredPane_1;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginDialog frame = new LoginDialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public LoginDialog() {
		this.setBounds(0, 0, 995, 650);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		layeredPaneB.setOpaque(true);
		layeredPaneB.setBounds(0, 0, 979, 626);
		layeredPaneB.setBackground(ColorPallete.mainColor_Light);
		getContentPane().add(layeredPaneB);
		
		layeredPane = new JLayeredPane();
		layeredPane.setOpaque(true);
		layeredPane.setBounds(0, 0, 542, 614);
		layeredPaneB.add(layeredPane);
		
		layeredPane_1 = new JLayeredPane();
		layeredPaneB.setLayer(layeredPane_1, 1);
		layeredPane_1.setOpaque(true);
		layeredPane_1.setBackground(ColorPallete.mainColor);
		layeredPane_1.setBounds(558, 0, 437, 614);
		layeredPaneB.add(layeredPane_1);
		
		WelcomeMsg = new JLabel("¡Estás en casa!");
		WelcomeMsg.setHorizontalAlignment(SwingConstants.CENTER);
		WelcomeMsg.setFont(new Font("Segoe UI", Font.BOLD, 56));
		WelcomeMsg.setForeground(ColorPallete.mainColor_Light);
		WelcomeMsg.setBounds(15, 141, 406, 116);
		layeredPane_1.add(WelcomeMsg);
		
		RegisterMsg = new JLabel("¡Gusto conocerte!");
		RegisterMsg.setHorizontalAlignment(SwingConstants.CENTER);
		RegisterMsg.setFont(new Font("Segoe UI", Font.BOLD, 48));
		RegisterMsg.setForeground(new Color(ColorPallete.mainColor_Dark.getRed(), ColorPallete.mainColor_Dark.getGreen(), ColorPallete.mainColor_Dark.getBlue(),0));
		RegisterMsg.setBounds(15, 141, 406, 116);
		layeredPane_1.add(RegisterMsg);
		
		loginLabel = new JLabel("Si aún no tienes una cuenta");
		loginLabel.setForeground(ColorPallete.mainColor_Light);
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.setFont(new Font("Segoe Print", Font.BOLD, 24));
		loginLabel.setBounds(25, 227, 377, 105);
		layeredPane_1.add(loginLabel);
		
		registerLabel = new JLabel("Si ya tienes una cuenta");
		registerLabel.setForeground(new Color(ColorPallete.mainColor_Dark.getRed(), ColorPallete.mainColor_Dark.getGreen(), ColorPallete.mainColor_Dark.getBlue(),0));
		registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		registerLabel.setFont(new Font("Segoe Print", Font.BOLD, 24));
		registerLabel.setBounds(0, 227, 437, 105);
		layeredPane_1.add(registerLabel);
		
		loginToggle = new JLabel("");
		
		loginToggle.setFont(new Font("Segoe Script", Font.BOLD, 45));
		
		lblBtn_R = new JLabel("Registro");
		layeredPane_1.setLayer(lblBtn_R, 1);
		lblBtn_R.setForeground(ColorPallete.mainColor_Light);
		lblBtn_R.setHorizontalAlignment(SwingConstants.CENTER);
		lblBtn_R.setFont(new Font("Segoe Print", Font.BOLD, 30));
		lblBtn_R.setBounds(99, 339, 238, 64);
		layeredPane_1.add(lblBtn_R);
		
		lblBtn_L = new JLabel("Login");
		layeredPane_1.setLayer(lblBtn_L, 1);
		lblBtn_L.setHorizontalAlignment(SwingConstants.CENTER);
		lblBtn_L.setForeground(new Color(ColorPallete.mainColor_Dark.getRed(), ColorPallete.mainColor_Dark.getGreen(), ColorPallete.mainColor_Dark.getBlue(),0));
		lblBtn_L.setFont(new Font("Segoe Print", Font.BOLD, 30));
		lblBtn_L.setBounds(99, 339, 238, 64);
		layeredPane_1.add(lblBtn_L);
		
		loginToggle.setBorder(new CircularBorder(ColorPallete.mainColor_Light, 3, 84));
		loginToggle.setBackground(ColorPallete.mainColor);
		loginToggle.setOpaque(true);
		
		loginToggle.setBounds(99, 339, 238, 64);
		layeredPane_1.add(loginToggle);
		
		TITLE = new JLabel("LOGIN");
		TITLE.setForeground(ColorPallete.mainColor_Dark);
		TITLE.setFont(new Font("Segoe UI", Font.BOLD, 68));
		TITLE.setHorizontalAlignment(SwingConstants.CENTER);
		TITLE.setBounds(0, 109, 542, 103);
		layeredPane.add(TITLE);
		
		TITLE_R = new JLabel("REGISTRO");
		TITLE_R.setForeground(ColorPallete.mainColor_Light);
		TITLE_R.setFont(new Font("Segoe UI", Font.BOLD, 68));
		TITLE_R.setHorizontalAlignment(SwingConstants.CENTER);
		TITLE_R.setBounds(0, 109, 542, 103);
		layeredPane.add(TITLE_R);
		
		Username = new JLabel("Username");
		Username.setForeground(ColorPallete.mainColor_Dark);
		Username.setFont(new Font("Segoe Print", Font.BOLD, 26));
		Username.setBounds(38, 240, 173, 60);
		layeredPane.add(Username);
		
		Lastname = new JLabel("Apellido");
		Lastname.setForeground(new Color(ColorPallete.mainColor_Light.getRed(), ColorPallete.mainColor_Light.getGreen(), ColorPallete.mainColor_Light.getBlue(),0));
		Lastname.setFont(new Font("Segoe Print", Font.BOLD, 26));
		Lastname.setBounds(38, 205, 173, 60);
		layeredPane.add(Lastname);
		
		lastnameField = new JTextField();
		lastnameField.setForeground(new Color(255, 255, 255, 0));
		lastnameField.setFont(new Font("Segoe Print", Font.PLAIN, 32));
		lastnameField.setBounds(209, 250, 270, 40);
		layeredPane.add(lastnameField);
		lastnameField.setColumns(10);
		
		Password = new JLabel("Contraseña");
		Password.setForeground(ColorPallete.mainColor_Dark);
		Password.setFont(new Font("Segoe Print", Font.BOLD, 26));
		Password.setBounds(38, 305, 191, 60);
		layeredPane.add(Password);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Segoe Print", Font.PLAIN, 32));
		passwordField.setBounds(209, 315, 270, 40);
		layeredPane.add(passwordField);
		
		Gender = new JLabel("Sexo");
		Gender.setForeground(new Color(ColorPallete.mainColor_Light.getRed(), ColorPallete.mainColor_Light.getGreen(), ColorPallete.mainColor_Light.getBlue(),0));
		Gender.setFont(new Font("Segoe Print", Font.BOLD, 26));
		Gender.setBounds(38, 415, 173, 60);
		layeredPane.setLayer(Gender, 0);
		layeredPane.add(Gender);
		
		
		maleBtn = new JRadioButton("Masculino");
		maleBtn.setSelected(true);
		femaleBtn = new JRadioButton("Femenino");
		maleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(maleBtn.isSelected()) {
					femaleBtn.setSelected(false);
				}
				else femaleBtn.setSelected(true);
			}
		});
		femaleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(femaleBtn.isSelected()) {
					maleBtn.setSelected(false);
				}
				else maleBtn.setSelected(true);
			}
		});
		maleBtn.setVisible(false);
		femaleBtn.setVisible(false);
		
		maleBtn.setBackground(ColorPallete.transparent);
		femaleBtn.setBackground(ColorPallete.transparent);
		
		maleBtn.setBounds(175, 415, 200, 60);
		maleBtn.setFont(new Font("Segoe Print", Font.BOLD, 24));
		maleBtn.setForeground(new Color(ColorPallete.mainColor_Light.getRed(), ColorPallete.mainColor_Light.getGreen(), ColorPallete.mainColor_Light.getBlue(),0));
		femaleBtn.setForeground(new Color(ColorPallete.mainColor_Light.getRed(), ColorPallete.mainColor_Light.getGreen(), ColorPallete.mainColor_Light.getBlue(),0));
		femaleBtn.setFont(new Font("Segoe Print", Font.BOLD, 24));
		femaleBtn.setBounds(359, 415, 200, 60);
		layeredPane.add(maleBtn);
		layeredPane.add(femaleBtn);
		
		Birthdate = new JLabel("Fecha Nac.");
		Birthdate.setForeground(new Color(ColorPallete.mainColor_Light.getRed(), ColorPallete.mainColor_Light.getGreen(), ColorPallete.mainColor_Light.getBlue(),0));
		Birthdate.setFont(new Font("Segoe Print", Font.BOLD, 26));
		Birthdate.setBounds(38, 335, 173, 60);
		layeredPane.setLayer(Birthdate, 0);
		layeredPane.add(Birthdate);
		
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		
		String[] months = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}; // January to December
        cbxMonth = new JComboBox<>(months);

        cbxMonth.setBounds(209, 315, 100, 40);
        cbxMonth.setFont(new Font("Segoe Print", Font.BOLD, 18));
        cbxMonth.setForeground(ColorPallete.mainColor_Dark);
        layeredPane.add(cbxMonth);

        yearSpinner = new JSpinner(new SpinnerNumberModel(currentYear, 1900, currentYear, 1));
        yearSpinner.addChangeListener(new ChangeListener() {
        	public void stateChanged(ChangeEvent e) {
        		updateDays();
        	}
        });
        yearSpinner.setBounds(339, 315, 70, 40);
        yearSpinner.setFont(new Font("Segoe Print", Font.BOLD, 14));
        yearSpinner.setForeground(ColorPallete.mainColor_Dark);
        layeredPane.add(yearSpinner);

        daySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
        daySpinner.setFont(new Font("Segoe Print", Font.BOLD, 18));
        daySpinner.setForeground(ColorPallete.mainColor_Dark);
        daySpinner.setBounds(429, 315, 50, 40);
        layeredPane.add(daySpinner);
        
        cbxMonth.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		updateDays();
        	}
        });
        
        daySpinner.setVisible(false);
        yearSpinner.setVisible(false);
        cbxMonth.setVisible(false);
		
		BUTTON = new JLabel("");
		layeredPane.setLayer(BUTTON, 1);
		BUTTON.setOpaque(true);
		BUTTON.setFont(new Font("Segoe Print", Font.BOLD, 45));
		BUTTON.setBorder(new CircularBorder(ColorPallete.mainColor_Dark, 3, 84));
		BUTTON.setBackground(new Color(102, 205, 170));
		BUTTON.setBounds(152, 410, 238, 64);
		layeredPane.add(BUTTON);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Segoe Print", Font.PLAIN, 32));
		usernameField.setBounds(209, 250, 270, 40);
		layeredPane.setLayer(usernameField, 1);
		layeredPane.add(usernameField);
		usernameField.setColumns(10);
		
		BUTTON_LABEL = new JLabel("Login");
		layeredPane.setLayer(BUTTON_LABEL, 2);
		BUTTON_LABEL.setHorizontalAlignment(SwingConstants.CENTER);
		BUTTON_LABEL.setForeground(new Color(212, 240, 235));
		BUTTON_LABEL.setFont(new Font("Segoe Print", Font.BOLD, 30));
		BUTTON_LABEL.setBounds(152, 410, 238, 64);
		layeredPane.add(BUTTON_LABEL);
		
		REGISTER_LABEL = new JLabel("Registro");
		layeredPane.setLayer(REGISTER_LABEL, 2);
		REGISTER_LABEL.setHorizontalAlignment(SwingConstants.CENTER);
		REGISTER_LABEL.setForeground(new Color(ColorPallete.mainColor.getRed(), ColorPallete.mainColor.getGreen(), ColorPallete.mainColor.getBlue(),0));
		REGISTER_LABEL.setFont(new Font("Segoe Print", Font.BOLD, 30));
		REGISTER_LABEL.setBounds(152, 410, 238, 64);
		layeredPane.add(REGISTER_LABEL);
		
		BUTTON.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	if(!isSelected) {
            		Animations.fadeIntoColorLabel(BUTTON_LABEL, ColorPallete.mainColor);
            		Animations.fadeIntoColor(BUTTON, ColorPallete.mainColor_Light);
            	}
            	else {
            		Animations.fadeIntoColorLabel(REGISTER_LABEL, ColorPallete.mainColor_Light);
            		Animations.fadeIntoColor(BUTTON, ColorPallete.mainColor);
            	}
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
            	if(!isSelected) {
            		Animations.fadeIntoColorLabel(BUTTON_LABEL, ColorPallete.mainColor_Light);
            		Animations.fadeIntoColor(BUTTON, ColorPallete.mainColor);
            	}
            	else {
            		Animations.fadeIntoColorLabel(REGISTER_LABEL, ColorPallete.mainColor);
            		Animations.fadeIntoColor(BUTTON, ColorPallete.mainColor_Light);
            	}
                setCursor(Cursor.getDefaultCursor());
            }
			@Override
			public void mouseClicked(MouseEvent e) {
            	if(!isSelected) {
            		Animations.fadeIntoColorLabel(BUTTON_LABEL, ColorPallete.mainColor_Light);
            		Animations.fadeIntoColor(BUTTON, ColorPallete.mainColor);
            		String Id = HospitalController.getInstance().loginUser(usernameField.getText(), new String(passwordField.getPassword()));
            		System.out.println("User: " + usernameField.getText() + "\nPassword: " + new String(passwordField.getPassword()));
            		if(Id != null) {
            			if(HospitalController.getInstance().findUserById(Id) instanceof Patient) {
            				DashboardPatient dp = new DashboardPatient();
            				 dp.getFrame().setVisible(true);
            				 dispose();
            			}
            		}
            	}
            	else {
            		Animations.fadeIntoColorLabel(BUTTON_LABEL, ColorPallete.mainColor);
            		Animations.fadeIntoColor(BUTTON, ColorPallete.mainColor_Light);
            		Calendar c = Calendar.getInstance();
            		c.set((int)yearSpinner.getValue(),(int)cbxMonth.getSelectedIndex(),(int)daySpinner.getValue());
            		HospitalController.getInstance().addPatient(new Patient(IdGenerator.generarID(3), usernameField.getText(), lastnameField.getText(), new String(passwordField.getPassword()), c.getTime(), 0, null, 0, 0));
            		
            		String Id = HospitalController.getInstance().loginUser(usernameField.getText(), new String(passwordField.getPassword()));
            		System.out.println("User: " + usernameField.getText() + "\nPassword: " + new String(passwordField.getPassword()));
            		
            		if(Id != null) {
            			if(HospitalController.getInstance().findUserById(Id) instanceof Patient) {
            				DashboardPatient dp = new DashboardPatient();
            				 dp.getFrame().setVisible(true);
            				 dispose();
            			}
            		}
            		
            	}
            	setCursor(Cursor.getDefaultCursor());
			}
		});
		
		
		loginToggle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	if(!isSelected) {
            		Animations.fadeIntoColorLabel(lblBtn_R, ColorPallete.mainColor);
            		Animations.fadeIntoColor(loginToggle, ColorPallete.mainColor_Light);
            	}
            	else {
            		Animations.fadeIntoColorLabel(lblBtn_L, ColorPallete.mainColor_Light);
            		Animations.fadeIntoColor(loginToggle, ColorPallete.mainColor);
            	}
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }     
            @Override
            public void mouseExited(MouseEvent e) {
            	if(!isSelected) {
            		Animations.fadeIntoColorLabel(lblBtn_R, ColorPallete.mainColor_Light);
            		Animations.fadeIntoColor(loginToggle, ColorPallete.mainColor);
            	}
            	else {
            		Animations.fadeIntoColorLabel(lblBtn_L, ColorPallete.mainColor_Dark);
            		Animations.fadeIntoColor(loginToggle, ColorPallete.mainColor_Light);
            	}
                setCursor(Cursor.getDefaultCursor());
            }
            @Override
            public void mouseClicked(MouseEvent e) {
            	isSelected = !isSelected;
				if(isSelected) {
					Animations.fadeIntoColor(layeredPane_1, ColorPallete.mainColor_Light);
					Animations.fadeIntoColor(layeredPane, ColorPallete.mainColor);
					Animations.fadeIntoColor(layeredPaneB, ColorPallete.mainColor);
					Animations.fadeIntoColor(loginToggle, ColorPallete.mainColor_Light);
					Animations.fadeIntoColor(BUTTON, ColorPallete.mainColor_Light);
					Animations.fadeIntoColor(layeredPane, ColorPallete.mainColor);
					loginToggle.setBorder(new CircularBorder(ColorPallete.mainColor_Dark, 3, 84));
					Animations.fadeInOutForeground(WelcomeMsg, false);
					Animations.fadeInOutForeground(loginLabel, false);
					Animations.fadeInOutForeground(RegisterMsg, true);
					Animations.fadeInOutForeground(registerLabel, true);
					Animations.fadeInOutForeground(lblBtn_R, false);
					Animations.fadeInOutForeground(lblBtn_L, true);
					Animations.fadeInOutForeground(BUTTON_LABEL, false);
					Animations.fadeInOutForeground(REGISTER_LABEL, true);
					Animations.fadeInOutForeground(TITLE, false);
					Animations.fadeInOutForeground(TITLE_R, true);
					Animations.fadeIntoColorLabel(Username, ColorPallete.mainColor_Light);
					Animations.fadeIntoColorLabel(Password, ColorPallete.mainColor_Light);
					Animations.moveCARDINAL(layeredPane_1, SwingConstants.WEST, null);
					Animations.moveCARDINAL(layeredPane, SwingConstants.EAST, LoginDialog.this);
				}
				else {
					Animations.fadeIntoColor(layeredPane_1, ColorPallete.mainColor);
					Animations.fadeIntoColor(layeredPane, ColorPallete.mainColor_Light);
					Animations.fadeIntoColor(layeredPaneB, ColorPallete.mainColor_Light);
					Animations.fadeIntoColor(loginToggle, ColorPallete.mainColor);
					Animations.fadeIntoColor(BUTTON, ColorPallete.mainColor);
					Animations.fadeIntoColor(layeredPane, ColorPallete.mainColor_Light);
					loginToggle.setBorder(new CircularBorder(ColorPallete.mainColor_Light, 3, 84));
					Animations.fadeInOutForeground(WelcomeMsg, true);
					Animations.fadeInOutForeground(loginLabel, true);
					Animations.fadeInOutForeground(RegisterMsg, false);
					Animations.fadeInOutForeground(registerLabel, false);
					Animations.fadeInOutForeground(lblBtn_R, true);
					Animations.fadeInOutForeground(lblBtn_L, false);
					Animations.fadeInOutForeground(BUTTON_LABEL, true);
					Animations.fadeInOutForeground(REGISTER_LABEL, false);
					Animations.fadeInOutForeground(TITLE, true);
					Animations.fadeInOutForeground(TITLE_R, false);
					Animations.fadeIntoColorLabel(Username, ColorPallete.mainColor_Dark);
					Animations.fadeIntoColorLabel(Password, ColorPallete.mainColor_Dark);
					Animations.moveCARDINAL(layeredPane_1, SwingConstants.EAST, null);
					Animations.moveCARDINAL(layeredPane, SwingConstants.WEST, LoginDialog.this);
				}
            }
		});

	}
	
	@Override
	public void OnAnimationFinished() {
		if(isSelected) {
			Animations.gotoXY(TITLE_R, TITLE_R.getX(), TITLE_R.getY()-95, null);
			Animations.gotoXY(Username, Username.getX(), Username.getY()-95, null);
			Animations.gotoXY(usernameField, usernameField.getX(), usernameField.getY()-95, null);
			Animations.gotoXY(REGISTER_LABEL, REGISTER_LABEL.getX(), REGISTER_LABEL.getY()+95, null);
			Animations.gotoXY(lastnameField, lastnameField.getX(), lastnameField.getY()-35, null);
			Animations.gotoXY(Password, Password.getX(), Password.getY()-35, null);
			Animations.gotoXY(passwordField, passwordField.getX(), passwordField.getY()-35, null);
			Animations.gotoXY(BUTTON, BUTTON.getX(), BUTTON.getY()+95, null);
			Animations.gotoXY(TITLE_R, TITLE_R.getX(), TITLE_R.getY()-95, null);
	        daySpinner.setVisible(true);
	        yearSpinner.setVisible(true);
	        cbxMonth.setVisible(true);
			Animations.gotoXY(daySpinner, daySpinner.getX(), daySpinner.getY()+30, null);
			Animations.gotoXY(yearSpinner, yearSpinner.getX(), yearSpinner.getY()+30, null);
			Animations.gotoXY(cbxMonth, cbxMonth.getX(), cbxMonth.getY()+30, null);
			Animations.fadeInOutForeground(Lastname, true);
			Animations.fadeInOutForeground(Birthdate, true);
			maleBtn.setVisible(true);
			femaleBtn.setVisible(true);
			Animations.fadeInOutForeground(Gender,true);
			Animations.fadeInOutForeground(maleBtn,true);
			Animations.fadeInOutForeground(femaleBtn,true);
		}
		else {
			maleBtn.setVisible(false);
			femaleBtn.setVisible(false);
	        daySpinner.setVisible(false);
	        yearSpinner.setVisible(false);
	        cbxMonth.setVisible(false);
		}
	}
	
	@Override
	public void OnAnimationStarted() {
		if(isSelected) {
			
		}
		else {
			Animations.gotoXY(TITLE_R, TITLE_R.getX(), TITLE_R.getY()+95, null);
			Animations.gotoXY(Username, Username.getX(), Username.getY()+95, null);
			Animations.gotoXY(usernameField, usernameField.getX(), usernameField.getY()+95, null);
			Animations.gotoXY(REGISTER_LABEL, REGISTER_LABEL.getX(), REGISTER_LABEL.getY()-95, null);
			Animations.gotoXY(lastnameField, lastnameField.getX(), lastnameField.getY()+35, null);
			Animations.gotoXY(Password, Password.getX(), Password.getY()+35, null);
			Animations.gotoXY(passwordField, passwordField.getX(), passwordField.getY()+35, null);
			Animations.gotoXY(BUTTON, BUTTON.getX(), BUTTON.getY()-95, null);
			Animations.gotoXY(TITLE_R, TITLE_R.getX(), TITLE_R.getY()+95, null);
			Animations.gotoXY(daySpinner, daySpinner.getX(), daySpinner.getY()-30, null);
			Animations.gotoXY(yearSpinner, yearSpinner.getX(), yearSpinner.getY()-30, null);
			Animations.gotoXY(cbxMonth, cbxMonth.getX(), cbxMonth.getY()-30, null);
			Animations.fadeInOutForeground(Lastname, false);
			Animations.fadeInOutForeground(Birthdate, false);
			Animations.fadeInOutForeground(Gender,false);
			Animations.fadeInOutForeground(maleBtn,false);
			Animations.fadeInOutForeground(femaleBtn,false);
		}
	}
	
    private void updateDays() {
        String month = (String)cbxMonth.getSelectedItem();
        int year = (int) yearSpinner.getValue();

        int maxDays;

        // Determine days in the month
        switch (month) {
            case "Febrero": 
                maxDays = (isLeapYear(year)) ? 29 : 28;
                break;
            case "Abril": case "Junio": case "Septiembre": case "Noviembre": // April, June, September, November
                maxDays = 30;
                break;
            default:
                maxDays = 31;
                break;
        }

        // Update day spinner with new maximum
        SpinnerNumberModel model = (SpinnerNumberModel)daySpinner.getModel();
        model.setMinimum(1);
        model.setMaximum(maxDays);

        // Adjust day if current value is out of bounds
        if ((int) daySpinner.getValue() > maxDays) {
            daySpinner.setValue(maxDays);
        }
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
	
}
