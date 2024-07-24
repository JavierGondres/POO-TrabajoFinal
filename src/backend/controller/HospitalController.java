package backend.controller;

import backend.classes.*;
import backend.classes.Record;
import backend.enums.*;
import backend.utils.IdGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    private AccessType access;
    private static HospitalController instance;
    private String currentUserId;

    public HospitalController() {
        this.employees = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.queries = new ArrayList<>();
        this.records = new HashMap<>();
        this.vaccines = new ArrayList<>();
        this.diseases = new ArrayList<>();
    }

    public static HospitalController getInstance() {
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

    public void addRecord(String patientId, Record record) {
        this.records.put(patientId, record);
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

    public void removeRecord(String patientId) {
        this.records.remove(patientId);
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

    public AccessType getAccessType() {
        return access;
    }

    private void setAccessType(AccessType access) {
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
    
    public String loginUser(String username, String password) {
    	for(Patient p: patients) {
    		if(p.getUserName().equals(username) && p.getPassword().equals(password)) {
    			setAccessType(AccessType.BAJO);
    			return p.getId();
    		}
    	}
    	for(Employee e: employees) {
    		if(e.getUserName().equals(username) && e.getPassword().equals(password)) {
    			if(e instanceof MedicalEmployee) setAccessType(AccessType.MEDIO);
    			else setAccessType(AccessType.ALTO);
    			return e.getId();
    		}
    	}
    	return null;
    }
    
    public User findUserById(String Id) {
    	for(Patient p: patients) {
    		if(p.getId().equals(Id)) {
    			return p;
    		}
    	}
    	for(Employee e: employees) {
    		if(e.getId().equals(Id)) {
    			return e;
    		}
    	}
    	return null;
    }
    
    
    
    public User getCurrentUser() {
    	return findUserById(currentUserId);
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

    // Función para validar las fechas de inicio y fin de una consulta {No debe chocar con el rango de una cita activa}

    public void registerQuery(String patientID, String doctorID, Date date, float fee, QueryTime queryTime, Date endDate) {
        Patient patient = findPatientById(patientID);
        MedicalEmployee doctor = (MedicalEmployee) findEmployeeById(doctorID);

        if (patient == null || doctor == null) {
            return;
        }

        Map<String, Query> queryMap = new HashMap<>();

        for (Query query : queries) {
            String key = query.getPatientID() + query.getDoctorID() + query.getDate().toString();
            queryMap.put(key, query);
        }

        String queryKey = patientID + doctorID + date.toString();

        if (!queryMap.containsKey(queryKey)) {
            Record patientRecord = records.get(patientID);
            if (patientRecord == null) {
                ArrayList<Disease> diseases = new ArrayList<>();
                ArrayList<Vaccine> vaccines = new ArrayList<>();
                patientRecord = new Record("SYMP", "DESC", diseases, diseases, vaccines, 100.0f, 100.0f, new Date());
                records.put(patientID, patientRecord);
            }

            Query newQuery = new Query(IdGenerator.generarID(), patientID, doctorID, fee, date, true, queryTime, endDate);
            queries.add(newQuery);
            System.out.println("Se registró la consulta correctamente");
        }
    }
}
