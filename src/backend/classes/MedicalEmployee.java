package backend.classes;

import backend.enums.Specialty;

import java.util.ArrayList;
import java.util.Date;

public class MedicalEmployee extends Employee {
    private ArrayList<String> queriesId;
    private ArrayList<Specialty> specialities;
    private String officeId;


    public MedicalEmployee(String id, String userName, String lastName, String password, Date birthday, float balance, ArrayList<Integer> workSchedule, ArrayList<String> queriesId, ArrayList<Specialty> specialities, String officeId) {
        super(id, userName, lastName, password, birthday, balance, workSchedule);
        this.queriesId = queriesId;
        this.specialities = specialities;
        this.officeId = officeId;
    }

    public ArrayList<String> getQueriesId() {
        return queriesId;
    }

    public void setQueriesId(ArrayList<String> queriesId) {
        this.queriesId = queriesId;
    }

    public ArrayList<Specialty> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(ArrayList<Specialty> specialities) {
        this.specialities = specialities;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public float calculateSalary() {
        int totalHours = 0;
        for (int quantityOfHours : workSchedule)
            totalHours += quantityOfHours;

        return getDefaultSalary() * totalHours;
    }

    @Override
    public float getDefaultSalary() {
        return 0;
    }
}
