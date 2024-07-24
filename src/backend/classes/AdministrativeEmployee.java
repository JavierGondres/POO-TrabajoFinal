package backend.classes;

import backend.enums.AccessType;

import java.io.File;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class AdministrativeEmployee extends Employee {
    private AccessType accessType;

    public AdministrativeEmployee(String id, String userName, String lastName, String password, Date birthday, float balance, LocalTime shiftStart, LocalTime shiftEnds, AccessType accessType, File photo) {
        super(id, userName, lastName, password, birthday, balance,shiftStart, shiftEnds,photo);
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
        if (accessType == AccessType.ALTO) {
            return 1200;
        } else if (accessType == AccessType.MEDIO) {
            return 750;
        } else {
            return 300;
        }
    }
}
