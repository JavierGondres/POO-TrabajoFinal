package backend.classes;

import backend.enums.AccessType;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class AdministrativeEmployee extends Employee {
    private AccessType accessType;

    public AdministrativeEmployee(String id, String userName, String lastName, String password, Date birthday, float balance, LocalTime shiftStart, LocalTime shiftEnds, AccessType accessType) {
        super(id, userName, lastName, password, birthday, balance,shiftStart, shiftEnds);
        this.accessType = accessType;
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
        return switch (accessType) {
            case ALTO -> 1200;
            case MEDIO -> 750;
            default -> 300;
        };
    }
}
