package backend.classes;

import backend.enums.AccesType;

import java.util.ArrayList;
import java.util.Date;

public class AdministrativeEmployee extends Employee{
    private AccesType accesType;

    public AdministrativeEmployee(String id, String userName, String lastName, String password, Date birthday, float balance, ArrayList<Integer> workSchedule, AccesType accesType) {
        super(id, userName, lastName, password, birthday, balance, workSchedule);
        this.accesType = accesType;
    }

    public AccesType getAccesType() {
        return accesType;
    }

    public void setAccesType(AccesType accesType) {
        this.accesType = accesType;
    }

    @Override
    public float calculateSalary() {
        return 0;
    }
}
