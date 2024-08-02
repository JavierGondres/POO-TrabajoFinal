package backend.classes;

import backend.enums.QueryTime;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class Query {
    private String id;
    private String patientID;
    private String doctorID;
    private float fee;
    private Date date;
    private boolean active;
    private QueryTime queryTime;
    private LocalTime startingTime;
    private LocalTime endingTime;

    public Query(String id, String patientID, String doctorID, float fee, Date date, boolean active, QueryTime queryTime, LocalTime startingTime, LocalTime endingTime) {
        this.id = id;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.fee = fee;
        this.date = date;
        this.active = active;
        this.queryTime = queryTime;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
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

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public QueryTime getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(QueryTime queryTime) {
        this.queryTime = queryTime;
    }

    public void postponeDate (Date newDate) {
        this.date = newDate;
    }

    public void cancelConsultation() {
        this.active = false;
    }

    public LocalTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(LocalTime startingTime) {
        this.startingTime = startingTime;
    }

    public LocalTime getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(LocalTime endingTime) {
        this.endingTime = endingTime;
    }

    public String serializeToJson() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = (date != null) ? dateFormat.format(date) : "null";

        // Convertir LocalTime a string en formato ISO
        String formattedStartingTime = (startingTime != null) ? startingTime.toString() : "null";
        String formattedEndingTime = (endingTime != null) ? endingTime.toString() : "null";

        // Convertir enum a string
        String queryTimeString = (queryTime != null) ? queryTime.name() : "null";

        return "{"
                + "Query: "
                + "\"id\":\"" + id + "\","
                + "\"patientID\":\"" + patientID + "\","
                + "\"doctorID\":\"" + doctorID + "\","
                + "\"fee\":" + fee + ","
                + "\"date\":\"" + formattedDate + "\","
                + "\"active\":" + active + ","
                + "\"queryTime\":\"" + queryTimeString + "\","
                + "\"startingTime\":\"" + formattedStartingTime + "\","
                + "\"endingTime\":\"" + formattedEndingTime + "\""
                + "}";
    }
}
