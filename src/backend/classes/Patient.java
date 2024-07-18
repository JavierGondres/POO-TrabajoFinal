package backend.classes;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class Patient extends User {

    private ArrayList<String> appointmentsId;

    public Patient(String id, String userName, String lastName, String password, Date birthday, float balance, ArrayList<String> appointmentsId, File profilePicture) {
        super(id, userName, lastName, password, birthday, balance, profilePicture);
        this.appointmentsId = appointmentsId;
    }

    public ArrayList<String> getAppointmentsId() {
        return appointmentsId;
    }

    public void setAppointmentsId(ArrayList<String> appointmentsId) {
        this.appointmentsId = appointmentsId;
    }
}
