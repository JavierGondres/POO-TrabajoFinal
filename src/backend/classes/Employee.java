package backend.classes;

import java.util.ArrayList;
import java.util.Date;

public abstract class Employee extends User {
    protected ArrayList<Integer> workSchedule;

    public Employee(String id, String userName, String lastName, String password, Date birthday, float balance, ArrayList<Integer> workSchedule) {
        super(id, userName, lastName, password, birthday, balance);
        this.workSchedule = workSchedule;
    }

    public ArrayList<Integer> getWorkHours() {
        return workSchedule;
    }

    public void setWorkHours(ArrayList<Integer> workSchedule) {
        this.workSchedule = workSchedule;
    }

    public abstract float calculateSalary();

    public abstract float getDefaultSalary();
}
