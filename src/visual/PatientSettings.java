package visual;
import visual.utils.Animations;
import visual.utils.ColorPallete;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Cursor;

public class PatientSettings extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	JPanel switchTheme;
	JPanel themeMode;
	private boolean lightMode = true;
	JPanel panel;

	public static void main(String[] args) {
		try {
			PatientSettings dialog = new PatientSettings();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PatientSettings() {
		setBounds(100, 100, 567, 541);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.setOpaque(true);
		contentPanel.setForeground(ColorPallete.mainColor_Light);
		
		panel = new JPanel();
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Settings");
		lblNewLabel.setForeground(ColorPallete.mainColor_Dark);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel.setBounds(55, 33, 66, 20);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Configuración de apariencia");
		lblNewLabel_1.setForeground(ColorPallete.mainColor_Dark);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1.setBounds(55, 87, 232, 20);
		panel.add(lblNewLabel_1);
		
		themeMode = new JPanel();
		themeMode.setBorder(new CircularBorder(Color.GRAY,1,35));
		themeMode.setBackground(ColorPallete.mainColor);
		themeMode.setBounds(129, 144, 299, 53);
		panel.add(themeMode);
		themeMode.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Tema");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_2.setBounds(32, 10, 45, 13);
		themeMode.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Encender el modo oscuro o el modo claro");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_3.setBounds(32, 33, 236, 13);
		themeMode.add(lblNewLabel_3);
		
		JPanel Option = new JPanel();
		Option.setBorder(new CircularBorder(Color.GRAY, 1, 15));
		Option.setBounds(226, 10, 51, 21);
		themeMode.add(Option);
		Option.setLayout(null);
		
		switchTheme = new JPanel();
		switchTheme.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				moveToggle();
				visualMode();
			}
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
            }
		});
		switchTheme.setBorder(new CircularBorder(Color.GRAY, 1, 15));
		switchTheme.setBackground(new Color(128, 0, 128));
		switchTheme.setForeground(new Color(128, 0, 128));
		switchTheme.setBounds(31, 3, 15, 15);
		Option.add(switchTheme);
		
		JPanel language = new JPanel();
		language.setBorder(new CircularBorder(Color.GRAY, 1, 35));
		language.setBackground(ColorPallete.mainColor);
		language.setBounds(129, 234, 299, 53);
		panel.add(language);
		language.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Idioma");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_4.setBounds(32, 10, 65, 13);
		language.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Seleccione el idioma de preferencia");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_5.setBounds(32, 30, 186, 13);
		language.add(lblNewLabel_5);
		
		JPanel ColorPalletePanel = new JPanel();
		ColorPalletePanel.setLayout(null);
		ColorPalletePanel.setBorder(new CircularBorder(Color.GRAY, 1, 35));
		ColorPalletePanel.setBackground(new Color(102, 205, 170));
		ColorPalletePanel.setBounds(129, 319, 299, 53);
		panel.add(ColorPalletePanel);
		
		JLabel lblNewLabel_4_1 = new JLabel("Paleta de color");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_4_1.setBounds(32, 10, 129, 13);
		ColorPalletePanel.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_5_1 = new JLabel("Seleccione la paleta de color deseada");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_5_1.setBounds(32, 30, 186, 13);
		ColorPalletePanel.add(lblNewLabel_5_1);
		
		JPanel SaveOption = new JPanel();
		SaveOption.setLayout(null);
		SaveOption.setBorder(new CircularBorder(Color.GRAY, 1, 65));
		SaveOption.setBackground(ColorPallete.componentColor);
		SaveOption.setBounds(129, 404, 299, 53);
		panel.add(SaveOption);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Guardar Selección");
		lblNewLabel_4_1_1.setForeground(ColorPallete.mainColor_Light);
		lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_4_1_1.setBounds(88, 20, 129, 13);
		SaveOption.add(lblNewLabel_4_1_1);
	}
private void visualMode() {
		
		if(lightMode) {
			themeMode.setBorder(new CircularBorder(ColorPallete.mainColor, 1,35));
			Animations.fadeIntoColor(panel, Color.LIGHT_GRAY);
			Animations.fadeIntoColor(contentPanel, Color.DARK_GRAY);
			Animations.fadeIntoColor(themeMode, ColorPallete.mainColor);
			Animations.fadeIntoColor(switchTheme, new Color(128, 0, 128));
		}
		else {
			themeMode.setBorder(new CircularBorder(ColorPallete.mainColor,1,35));
			themeMode.setBorder(new CircularBorder(ColorPallete.mainColor,1,35));
			Animations.fadeIntoColor(panel, Color.DARK_GRAY);
			Animations.fadeIntoColor(contentPanel, Color.LIGHT_GRAY);
			Animations.fadeIntoColor(themeMode,ColorPallete.mainColor);
			Animations.fadeIntoColor(switchTheme, new Color(128, 0, 128));
		}			
	}
	
	private void moveToggle() {
    	if(lightMode) Animations.gotoXY(switchTheme, 5, switchTheme.getY(), null);
    	else Animations.gotoXY(switchTheme, 31, switchTheme.getY(), null);
    	
    	lightMode = !lightMode;
	}
}
