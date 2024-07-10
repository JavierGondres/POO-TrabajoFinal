package classes;

import java.util.Date;

public class Room {
    private String id;
    private String patirntID;
    private String doctorID;
    private boolean available;
    private Date date;

    public Room(String id, String patirntID, String doctorID, boolean available, Date date) {
        this.id = id;
        this.patirntID = patirntID;
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

    public String getPatirntID() {
        return patirntID;
    }

    public void setPatirntID(String patirntID) {
        this.patirntID = patirntID;
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
}
