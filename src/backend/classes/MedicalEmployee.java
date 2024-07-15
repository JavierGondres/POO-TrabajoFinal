package backend.classes;

import backend.enums.Specialty;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalTime;

public class MedicalEmployee extends Employee {
    private ArrayList<Specialty> specialities;

    public MedicalEmployee(String id, String userName, String lastName, String password, Date birthday, float balance,
                           ArrayList<Specialty> specialities, LocalTime doctorShiftStart, LocalTime doctorShiftEnd) {
        super(id, userName, lastName, password, birthday, balance, doctorShiftStart, doctorShiftEnd);
        this.specialities = specialities;
    }

    public ArrayList<Specialty> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(ArrayList<Specialty> specialities) {
        this.specialities = specialities;
    }

    public float calculateSalary() {
        return getDefaultSalary() * calculateWorkSchedule();
    }

    @Override
    public float getDefaultSalary() {
        return 0;
    }
}
