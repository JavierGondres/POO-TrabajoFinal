package backend.classes;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public abstract class Employee extends User {
    protected LocalTime shiftStart;
    protected LocalTime shiftEnd;

    public Employee(String id, String userName, String lastName, String password, Date birthday, float balance , LocalTime shiftStart, LocalTime shiftEnd, File profilePicture) {
        super(id, userName, lastName, password, birthday, balance, profilePicture);
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

    public String serializeToJson() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String birthday = dateFormat.format(this.getBirthday()); // Asume que getBirthday() es un método en User

        // La ruta del archivo se convierte en una cadena
        String profilePicturePath = (this.getProfilePicture() != null) ? this.getProfilePicture().getAbsolutePath() : "null";

        // Formatear las horas de inicio y fin
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String shiftStartStr = (this.shiftStart != null) ? this.shiftStart.format(timeFormatter) : "null";
        String shiftEndStr = (this.shiftEnd != null) ? this.shiftEnd.format(timeFormatter) : "null";

        return "{"
                + "\"id\":\"" + getId() + "\","
                + "\"userName\":\"" + getUserName() + "\","
                + "\"lastName\":\"" + getLastName() + "\","
                + "\"password\":\"" + getPassword() + "\","
                + "\"birthday\":\"" + birthday + "\","
                + "\"balance\":" + getBalance() + ","
                + "\"profilePicture\":\"" + profilePicturePath + "\","
                + "\"shiftStart\":\"" + shiftStartStr + "\","
                + "\"shiftEnd\":\"" + shiftEndStr + "\","
                + "\"workSchedule\":" + calculateWorkSchedule() // Ejemplo de uso de un método para obtener información adicional
                + "}";
    }
}
