package backend.classes;

import backend.enums.QueryTime;
import backend.enums.Specialty;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalTime;

public class MedicalEmployee extends Employee {
    private ArrayList<Specialty> specialities;
    private QueryTime queryTime;
    private float queryPrice;

    public MedicalEmployee(String id, String userName, String lastName, String password, Date birthday, float balance,
                           ArrayList<Specialty> specialities, LocalTime doctorShiftStart, LocalTime doctorShiftEnd, File profilePicture, QueryTime queryTime, float queryPrice) {
        super(id, userName, lastName, password, birthday, balance, doctorShiftStart, doctorShiftEnd, profilePicture);
        this.specialities = specialities;
        this.queryTime = queryTime;
        this.queryPrice = queryPrice;
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

    public QueryTime getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(QueryTime queryTime) {
        this.queryTime = queryTime;
    }

    public int getIntervalMinutesOfQueryTime() {
        if (queryTime == QueryTime.THIRTY_MINUTES) {
            return 30;
        } else if (queryTime == QueryTime.SIXTY_MINUTES) {
            return 60;
        } else if (queryTime == QueryTime.NINETY_MINUTES) {
            return 90;
        } else if (queryTime == QueryTime.ONE_HUNDRED_TWENTY_MINUTES) {
            return 120;
        } else {
            throw new IllegalArgumentException("Unexpected value: " + queryTime);
        }
    }
    
    public ArrayList<LocalTime> getRangeOfQueryTime() {
        ArrayList<LocalTime> rangeOfTimes = new ArrayList<>();
        int intervalMinutes = getIntervalMinutesOfQueryTime();

        LocalTime currentTime = shiftStart;
        LocalTime endTime = shiftEnd;

        while (currentTime.plusMinutes(intervalMinutes).isBefore(endTime) || currentTime.plusMinutes(intervalMinutes).equals(endTime)) {
            rangeOfTimes.add(currentTime);
            currentTime = currentTime.plusMinutes(intervalMinutes);
        }

        return rangeOfTimes;
    }

    public float getQueryPrice() {
        return queryPrice;
    }

    public void setQueryPrice(float queryPrice) {
        this.queryPrice = queryPrice;
    }
}
