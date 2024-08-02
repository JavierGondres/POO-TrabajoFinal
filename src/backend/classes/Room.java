package backend.classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Room {
    private String id;
    private String patientID;
    private String doctorID;
    private boolean available;
    private Date date;

    public Room(String id, String patientID, String doctorID, boolean available, Date date) {
        this.id = id;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.available = available;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String serializeToJson() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Convertir la fecha a cadena en formato JSON
        String formattedDate = (date != null) ? dateFormat.format(date) : "null";

        return "{"
                + "Room: "
                + "\"id\":\"" + id + "\","
                + "\"patientID\":\"" + patientID + "\","
                + "\"doctorID\":\"" + doctorID + "\","
                + "\"available\":" + available + ","
                + "\"date\":\"" + formattedDate + "\""
                + "}";
    }
}
