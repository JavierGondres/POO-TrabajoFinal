package backend.classes;

import backend.enums.AccessType;

import java.util.ArrayList;
import java.util.Date;

public class AdministrativeEmployee extends Employee {
    private AccessType accessType;

    public AdministrativeEmployee(String id, String userName, String lastName, String password, Date birthday, float balance, ArrayList<Integer> workSchedule, AccessType accessType) {
        super(id, userName, lastName, password, birthday, balance, workSchedule);
        this.accessType = accessType;
    }

    public AccessType getAccessType() {
        return accessType;
    }

    public void setAccessType(AccessType accessType) {
        this.accessType = accessType;
    }

    @Override
    public float calculateSalary() {
        int totalHours = 0;
        for (int quantityOfHours : workSchedule)
            totalHours += quantityOfHours;

        return getDefaultSalary() * totalHours;
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
