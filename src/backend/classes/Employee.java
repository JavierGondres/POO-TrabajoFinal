package backend.classes;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public abstract class Employee extends User {
    protected LocalTime shiftStart;
    protected LocalTime shiftEnd;

    public Employee(String id, String userName, String lastName, String password, Date birthday, float balance , LocalTime shiftStart, LocalTime shiftEnd) {
        super(id, userName, lastName, password, birthday, balance);
        this.shiftStart = shiftStart;
        this.shiftEnd = shiftEnd;
    }

    public LocalTime getShiftStart() {
        return shiftStart;
    }

    public void setShiftStart(String doctorShiftStart) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            this.shiftStart = LocalTime.parse(doctorShiftStart, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de hora inválido. Por favor, ingrese la hora en formato HH:mm en formato de 24 horas.");
        }
    }

    public LocalTime getShiftEnd() {
        return shiftEnd;
    }

    public void setShiftEnd(String doctorShiftEnd) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            this.shiftEnd = LocalTime.parse(doctorShiftEnd, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de hora inválido. Por favor, ingrese la hora en formato HH:mm en formato de 24 horas.");
        }
    }

    public int calculateWorkSchedule() {
        return shiftEnd.getHour() - shiftStart.getHour();
    }

    public abstract float calculateSalary();

    public abstract float getDefaultSalary();
}
