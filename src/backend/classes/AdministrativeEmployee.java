package backend.classes;

import backend.enums.AccessType;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class AdministrativeEmployee extends Employee {
    private AccessType accessType;

    public AdministrativeEmployee(String id, String userName, String lastName, String password, Date birthday, float balance, LocalTime shiftStart, LocalTime shiftEnds, AccessType accessType, File photo) {
        super(id, userName, lastName, password, birthday, balance,shiftStart, shiftEnds,photo);
        this.accessType = accessType;
    }

    public LocalTime getShiftEnds() {
        return super.getShiftEnd();
    }

    @Override
    public LocalTime getShiftStart() {
        return super.getShiftStart();
    }

    public AccessType getAccessType() {
        return accessType;
    }

    public void setAccessType(AccessType accessType) {
        this.accessType = accessType;
    }

    @Override
    public float calculateSalary() { //Fix this
        return getDefaultSalary() * calculateWorkSchedule();
    }

    @Override
    public float getDefaultSalary() {
        if (accessType == AccessType.ALTO) {
            return 1200;
        } else if (accessType == AccessType.MEDIO) {
            return 750;
        } else {
            return 300;
        }
    }

    public String serializeToJson() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String birthday = dateFormat.format(this.getBirthday()); // Asume que getBirthday() es un método en Employee


        // Convertir LocalTime a string en formato "HH:mm:ss"
        String shiftStart = (this.getShiftStart() != null) ? this.getShiftStart().toString() : "null";
        String shiftEnds = (this.getShiftEnds() != null) ? this.getShiftEnds().toString() : "null";

        return "{"
                + "Administrative Employee: "
                + "\"id\":\"" + getId() + "\","
                + "\"userName\":\"" + getUserName() + "\","
                + "\"lastName\":\"" + getLastName() + "\","
                + "\"password\":\"" + getPassword() + "\","
                + "\"birthday\":\"" + birthday + "\","
                + "\"balance\":" + getBalance() + ","
                + "\"shiftStart\":\"" + shiftStart + "\","
                + "\"shiftEnds\":\"" + shiftEnds + "\","
                + "\"accessType\":\"" + (accessType != null ? accessType.toString() : "null") + "\","
                + "}";
    }
}
