package visual.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;

public class DoctorCard extends UserCard {
    
    private JLabel labelSpecialities;

    public DoctorCard(String profilePicture, String userName, String specialities) {
        super(profilePicture, userName);

        labelSpecialities = new JLabel(specialities);
        labelSpecialities.setForeground(Color.decode("#0f1c30"));
        labelSpecialities.setFont(labelSpecialities.getFont().deriveFont(Font.PLAIN, 16));
        labelSpecialities.setBounds(12, 238, 190, 22);
        this.add(labelSpecialities);

    }
    
    @Override
    protected void updateTextColor(boolean isHovering) {
        super.updateTextColor(isHovering);
        if (isHovering || isSelected()) {
            labelSpecialities.setForeground(hoverTextColor);
        } else {
            labelSpecialities.setForeground(defaultTextColor);
        }
    }

    @Override
    public void toggleSelection() {
        super.toggleSelection();
        updateTextColor(isSelected());
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        updateTextColor(selected);
    }
}
