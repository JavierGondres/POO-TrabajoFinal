package backend.controller;

import classes.*;

import Enum.Genre;
import Enum.Priority;
import Enum.Specialty;
import classes.Record;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class HospitalController {
    private ArrayList<Employee> employees;
    private ArrayList<Patient> patients;
    private ArrayList<Room> rooms;
    private ArrayList<Query> queries;
    private HashMap<String, Record> records;
    private ArrayList<Vaccine> vaccines;
    private ArrayList<Disease> diseases;
    private Priority priority;
    private Genre genre;
    private Specialty specialty;
    private Access access;
    private static HospitalController instance;

    public HospitalController() {
        this.employees = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.queries = new ArrayList<>();
        this.records = new HashMap<>();
        this.vaccines = new ArrayList<>();
        this.diseases = new ArrayList<>();
    }

    public HospitalController getInstance() {
        if (instance == null) {
            instance = new HospitalController();
        }
        return instance;
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public void addPatient(Patient patient) {
        this.patients.add(patient);
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    public void addConsultation(Query query) {
        this.queries.add(query);
    }

    public void addRecord(Record record) {
        this.records.put(record.getSymptoms(), record);
    }

    public void addVaccine(Vaccine vaccine) {
        this.vaccines.add(vaccine);
    }

    public void addDisease(Disease disease) {
        this.diseases.add(disease);
    }

    public void removeEmployee(Employee employee) {
        this.employees.remove(employee);
    }

    public void removePatient(Patient patient) {
        this.patients.remove(patient);
    }

    public void removeRoom(Room room) {
        this.rooms.remove(room);
    }

    public void removeConsultation(Query query) {
        this.queries.remove(query);
    }

    public void removeRecord(Record record) {
        this.records.remove(record.getSymptoms());
    }

    public void removeVaccine(Vaccine vaccine) {
        this.vaccines.remove(vaccine);
    }

    public void removeDisease(Disease disease) {
        this.diseases.remove(disease);
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<Query> getConsultations() {
        return queries;
    }

    public HashMap<String, Record> getRecords() {
        return records;
    }

    public ArrayList<Vaccine> getVaccines() {
        return vaccines;
    }

    public ArrayList<Disease> getDiseases() {
        return diseases;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public void setConsultations(ArrayList<Query> queries) {
        this.queries = queries;
    }

    public void setRecords(HashMap<String, Record> records) {
        this.records = records;
    }

    public void setVaccines(ArrayList<Vaccine> vaccines) {
        this.vaccines = vaccines;
    }

    public void setDiseases(ArrayList<Disease> diseases) {
        this.diseases = diseases;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    public Employee findEmployeeById(String id) {
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                return employee;
            }
        }
        return null;
    }

    public Patient findPatientById(String id) {
        for (Patient patient : patients) {
            if (patient.getId().equals(id)) {
                return patient;
            }
        }
        return null;
    }

    public Room findRoomById(String id) {
        for (Room room : rooms) {
            if (room.getId().equals(id)) {
                return room;
            }
        }
        return null;
    }

    public Query findQueryById(String id) {
        for (Query query : queries) {
            if (query.getId().equals(id)) {
                return query;
            }
        }
        return null;
    }

    public Vaccine findVaccineById(String id) {
        for (Vaccine vaccine : vaccines) {
            if (vaccine.getId().equals(id)) {
                return vaccine;
            }
        }
        return null;
    }

    public Disease findDiseaseById(String id) {
        for (Disease disease : diseases) {
            if (disease.getId().equals(id)) {
                return disease;
            }
        }
        return null;
    }

    public void registerQuery(String patientID, String doctorID, Date date) {
        Patient patient = findPatientById(patientID);
        Employee doctor = findEmployeeById(doctorID);

        if (patient != null && doctor != null) {
            for(Query query : queries){
                if(doctor.getQueryID().equals(query.getId()) && !query.isActive() && !(query.getPatientID().equals(patientID)) &&
                        !(query.getDoctorID().equals(doctorID))){
                    query = new Query("0", patientID, doctorID, 0, date, patient.getRecord(), true);
                    queries.add(query);
                }
            }
        }
    }
}
