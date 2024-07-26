package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import backend.classes.Patient;
import visual.components.CustomTextField;

public class PatientInformation extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JLabel lblTitle = new JLabel("Paciente");
    private CustomTextField idTextField = new CustomTextField();
    private CustomTextField customTextFieldAltura = new CustomTextField();
    private JDateChooser dateChooser = new JDateChooser();
    private JButton cancelButton = new JButton("Cerrar");
    private CustomTextField customTextFieldPeso = new CustomTextField();
    private Patient patient;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PatientInformation dialog = new PatientInformation(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PatientInformation(Patient patient) {
		setResizable(false);
		this.patient = patient; 
		initalizeForm();
        setBounds(100, 100, 602, 374);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(Color.decode("#c0d0ef"));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setLocationRelativeTo(null);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.decode("#0f1c30"));
        headerPanel.setBounds(0, 0, 584, 50);
        headerPanel.setLayout(null);
        lblTitle.setBounds(12, 13, 560, 25);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD, 20));
        headerPanel.add(lblTitle);
        contentPanel.add(headerPanel);

        customTextFieldAltura.setEditable(false);
        customTextFieldAltura.setColumns(10);
        customTextFieldAltura.setBounds(109, 140, 167, 22);
        contentPanel.add(customTextFieldAltura);

        JLabel lblAltura = new JLabel("Altura:");
        lblAltura.setForeground(Color.decode("#0f1c30"));
        lblAltura.setFont(lblAltura.getFont().deriveFont(Font.BOLD, 14));
        lblAltura.setBounds(12, 138, 67, 25);
        contentPanel.add(lblAltura);

        JLabel lblFecha = new JLabel("Fecha de nacimiento:");
        lblFecha.setForeground(Color.decode("#0f1c30"));
        lblFecha.setFont(lblFecha.getFont().deriveFont(Font.BOLD, 14));
        lblFecha.setBounds(12, 185, 170, 25);
        contentPanel.add(lblFecha);

        dateChooser.setBounds(178, 188, 167, 22);
        dateChooser.setEnabled(false);
        contentPanel.add(dateChooser);
        

        customTextFieldPeso.setEditable(false);
        customTextFieldPeso.setColumns(10);
        customTextFieldPeso.setBounds(109, 89, 167, 22);
        contentPanel.add(customTextFieldPeso);
        
        JLabel lblPeso = new JLabel("Peso:");
        lblPeso.setForeground(Color.decode("#0f1c30"));
        lblPeso.setFont(lblPeso.getFont().deriveFont(Font.BOLD, 14));
        lblPeso.setBounds(12, 87, 67, 25);
        contentPanel.add(lblPeso);

        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                cancelButton.setActionCommand("Cancel");
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                buttonPane.add(cancelButton);
            }
        }
	}
	
	
	public void initalizeForm() {
        if(patient == null)
        	return;
        
        lblTitle.setText(patient.getUserName() + " " + patient.getLastName());
        customTextFieldAltura.setText(String.valueOf(patient.getHeight()));
        customTextFieldPeso.setText(String.valueOf(patient.getWeigth()));
        dateChooser.setDate(patient.getBirthday());
	}
}
