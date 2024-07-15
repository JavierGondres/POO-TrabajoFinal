package backend.classes;

import backend.enums.QueryTime;
import java.util.Date;

public class Query {
    private String id;
    private String patientID;
    private String doctorID;
    private float fee;
    private Date date;
    private boolean active;
    private QueryTime queryTime;
    private Date endDate;

    public Query(String id, String patientID, String doctorID, float fee, Date date, boolean active, QueryTime queryTime, Date endDate) {
        this.id = id;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.fee = fee;
        this.date = date;
        this.active = active;
        this.queryTime = queryTime;
        this.endDate = endDate;
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void postponeDate (Date newDate) {
        this.date = newDate;
    }

    public void cancelConsultation() {
        this.active = false;
    }
}
