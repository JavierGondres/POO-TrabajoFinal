package backend.controller;

import backend.classes.*;
import backend.classes.Record;
import backend.enums.*;

import java.io.File;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

import static backend.enums.AccessType.ALTO;

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
    private Patient currentPatient;
    private String currentUserId;
    private AdministrativeEmployee currentAdminEmployee;
    private MedicalEmployee currentMedicalEmployee;
    private static HospitalController instance;

    public HospitalController() {
        this.employees = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.queries = new ArrayList<>();
        this.records = new HashMap<>();
        this.vaccines = new ArrayList<>();
        this.diseases = new ArrayList<>();
        this.currentPatient = new Patient("999", "Javier Emilio", "Gondres", "123456", new Date(), 1000, null, 100, 160);
        this.currentMedicalEmployee = new MedicalEmployee("1", "Jane", "Doe", "1234", new Date(), 1000, new ArrayList<Specialty>(), LocalTime.of(9, 0), LocalTime.of(12, 0), new File("C:\\Users\\Scarlet\\OneDrive\\Documentos\\Java Proyects\\POO-TrabajoFinal\\src\\backend\\GeneralFile.txt"), QueryTime.THIRTY_MINUTES, 200);
        this.currentAdminEmployee = new AdministrativeEmployee("2", "Scarlet", "Abreu", "1234", new Date(), 1000, LocalTime.of(8, 0), LocalTime.of(16, 0), ALTO, new File("C:\\Users\\Scarlet\\OneDrive\\Documentos\\Java Proyects\\POO-TrabajoFinal\\src\\backend\\GeneralFile.txt"));
        this.patients.add(currentPatient);
        this.employees.add(currentMedicalEmployee);
        createQuery("hola", "999", "1", 5F, new Date(), null, LocalTime.NOON, LocalTime.MIDNIGHT);
    }

    public static HospitalController getInstance() {
        if (instance == null) {
            instance = new HospitalController();
        }
        return instance;
    }

    public MedicalEmployee getCurrentMedicalEmployee() {
        return currentMedicalEmployee;
    }

    public void setCurrentMedicalEmployee(MedicalEmployee currentMedicalEmployee) {
        this.currentMedicalEmployee = currentMedicalEmployee;
    }

    public AdministrativeEmployee getCurrentAdminEmployee() {
        return currentAdminEmployee;
    }

    public void setCurrentAdminEmployee(AdministrativeEmployee currentAdminEmployee) {
        this.currentAdminEmployee = currentAdminEmployee;
    }
    
    public String loginUser(String username, String password) {
    	for(Patient p: patients) {
    		if(p.getUserName().equals(username) && p.getPassword().equals(password)) {
    			setAccessType(AccessType.BAJO);
    			currentUserId = p.getId();
    			currentPatient = p;
    			return currentUserId;
    		}
    	}
    	for(Employee e: employees) {
    		if(e.getUserName().equals(username) && e.getPassword().equals(password)) {
    			if(e instanceof MedicalEmployee) setAccessType(AccessType.MEDIO);
    			else setAccessType(ALTO);
    			currentUserId = e.getId();
    			return currentUserId;
    		}
    	}
    	return null;
    }
    
    public User getCurrentUser() {
    	return findUserById(currentUserId);
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

    public void removeEmployee(String id) throws IllegalArgumentException {
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                employees.remove(employee);
                return;
            }
        }
        throw new IllegalArgumentException("Employee not found with ID: " + id);
    }

    public void removePatient(String id) throws IllegalArgumentException {
        for (Patient patient : patients) {
            if (patient.getId().equals(id)) {
                patients.remove(patient);
                return;
            }
        }
        throw new IllegalArgumentException("Patient not found with ID: " + id);
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

    public Patient getCurrentPatient() {
        return currentPatient;
    }

    public void setCurrentPatient(Patient currentPatient) {
        this.currentPatient = currentPatient;
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

    public void updateRecord(String patientId, Record updatedRecord) {
        if (!records.containsKey(patientId)) {
            throw new IllegalArgumentException("No se encontró un registro para el paciente con ID: " + patientId);
        }

        records.put(patientId, updatedRecord);
    }

    public Record getRecord(String patientId) {
        if (!records.containsKey(patientId)) {
            throw new IllegalArgumentException("No se encontró un registro para el paciente con ID: " + patientId);
        }

        Record record =  records.get(patientId);
        
        return record;
    }

    public ArrayList<MedicalEmployee> getMedicalEmployees() {
        ArrayList<MedicalEmployee> medicalEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee instanceof MedicalEmployee)
                medicalEmployees.add((MedicalEmployee) employee);
        }

        return medicalEmployees;
    }

    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> employeesList = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee instanceof Employee)
                employeesList.add((Employee) employee);
        }

        return employeesList;
    }

    public ArrayList<Patient> getPatientsFromMedicalEmployee(String doctorId){
        ArrayList<Patient> patientsFromDoctor = new ArrayList<>();

        for(Query query: queries){
            if(query.getDoctorID().equals(doctorId)){
                Patient patient = findPatientById(query.getPatientID());
                patientsFromDoctor.add(patient);
            }
        }

        return patientsFromDoctor;
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

    public void setAccessType(AccessType access) {
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
    


    public ArrayList<Query> getMedicalEmployeeActiveQueries(String doctorId) {
        ArrayList<Query> medicalEmployeeQueries = new ArrayList<>();

        for (Query query : queries) {
            if (query.isActive() && query.getDoctorID().equals(doctorId))
                medicalEmployeeQueries.add(query);
        }

        return medicalEmployeeQueries;
    }

    public ArrayList<Query> getPatientActiveQueries(String patientId) {
        ArrayList<Query> patientQueries = new ArrayList<>();

        for (Query query : queries) {
            if (query.isActive() && query.getPatientID().equals(patientId))
                patientQueries.add(query);
        }

        return patientQueries;
    }

    public void validateQuery(String patientID, String doctorID, Date queryDate, LocalTime startingTime, LocalTime endingTime) {
        ArrayList<Query> patientQueries = getPatientActiveQueries(patientID);
        ArrayList<Query> doctorQueries = getMedicalEmployeeActiveQueries(doctorID);
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(queryDate);
        
        for (Query query : patientQueries) {
            cal1.setTime(query.getDate());
            
            boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                              cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                              cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
            
            if (sameDay && isTimeOverlap(query.getStartingTime(), query.getEndingTime(), startingTime, endingTime)) {
                throw new IllegalStateException("Ya tienes una cita registrada para esa fecha y hora.");
            }
        }

        for (Query query : doctorQueries) {
            cal1.setTime(query.getDate());
            
            boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                              cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                              cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
            
            if (sameDay && isTimeOverlap(query.getStartingTime(), query.getEndingTime(), startingTime, endingTime)) {
                throw new IllegalStateException("Esta cita esta ocupada por otro paciente");
            }
        }
        
    }
    
    private boolean isTimeOverlap(LocalTime start1, LocalTime end1, LocalTime start2, LocalTime end2) {
        boolean startsDuringExistingQuery = start2.isBefore(end1) && start2.isAfter(start1);
        boolean endsDuringExistingQuery = end2.isAfter(start1) && end2.isBefore(end1);
        boolean startsAtSameTime = start2.equals(start1);
        boolean endsAtSameTime = end2.equals(end1);

        return startsDuringExistingQuery || endsDuringExistingQuery || startsAtSameTime || endsAtSameTime;
    }

    public void createQuery(String id, String patientID, String doctorID, float fee, Date date, QueryTime queryTime, LocalTime startingTime, LocalTime endingTime) {
        try {

        	Patient patient = findPatientById(patientID);
            MedicalEmployee doctor = (MedicalEmployee) findEmployeeById(doctorID);

            if (patient == null) {
                throw new IllegalArgumentException("Patient not found with ID: " + patientID);
            }

            if (doctor == null) {
                throw new IllegalArgumentException("Doctor not found with ID: " + doctorID);
            }

            validateQuery(patientID, doctorID, date, startingTime, endingTime);
            System.out.println("AA");

            Query query = new Query(id, patientID, doctorID, fee, date, true, queryTime, startingTime, endingTime);
            queries.add(query);
            initializePatientRecord(patient);
        }
        catch(IllegalStateException e) {
        	throw e;
        }

    }

    public void initializePatientRecord(Patient patient) {
        String id = patient.getId();
        if (records.containsKey(id))
            return;

        ArrayList<Disease> diseases = new ArrayList<>();
        ArrayList<Vaccine> vaccines = new ArrayList<>();
        Record patientRecord = new Record(id, "", "", diseases, vaccines, patient.getWeigth(), patient.getHeight(), new Date());
        records.put(id, patientRecord);

    }
    
    public void updateQuery(String id, Date date, LocalTime startingTime, LocalTime endingTime) {
        Query existingQuery = findQueryById(id);
        
        if (existingQuery == null) {
            throw new IllegalArgumentException("No se encontró una cita con el ID: " + id);
        }
        
        validateQuery(existingQuery.getPatientID(), existingQuery.getDoctorID(), date, startingTime, endingTime);

        existingQuery.setDate(date);
        existingQuery.setStartingTime(startingTime);
        existingQuery.setEndingTime(endingTime);
    }
    
    public void updateRoom(Room room) {
    	Room existingRom = findRoomById(room.getId());
        
        if (existingRom == null) {
            throw new IllegalArgumentException("No se encontro la habitacion con el ID: " + room.getId());
        }
        
        existingRom.setDate(room.getDate());
        existingRom.setPatientID(room.getPatientID());
        existingRom.setDoctorID(room.getDoctorID());
        existingRom.setAvailable(room.isAvailable());
    }

    public String serializeToJson() {

        String employeesJson = employees.stream()
                .map(Employee::serializeToJson) // Necesitarás implementar esto en la clase Employee
                .collect(Collectors.joining(",", "[", "]"));

        String patientsJson = patients.stream()
                .map(Patient::serializeToJson) // Necesitarás implementar esto en la clase Patient
                .collect(Collectors.joining(",", "[", "]"));

        String roomsJson = rooms.stream()
                .map(Room::serializeToJson) // Necesitarás implementar esto en la clase Room
                .collect(Collectors.joining(",", "[", "]"));

        String queriesJson = queries.stream()
                .map(Query::serializeToJson) // Necesitarás implementar esto en la clase Query
                .collect(Collectors.joining(",", "[", "]"));

        String recordsJson = records.entrySet().stream()
                .map(entry -> "{\"patientId\":\"" + entry.getKey() + "\", \"record\":" + entry.getValue().serializeToJson() + "}") // Necesitarás implementar esto en la clase Record
                .collect(Collectors.joining(",", "[", "]"));

        String vaccinesJson = vaccines.stream()
                .map(Vaccine::serializeToJson) // Necesitarás implementar esto en la clase Vaccine
                .collect(Collectors.joining(",", "[", "]"));

        String diseasesJson = diseases.stream()
                .map(Disease::serializeToJson) // Necesitarás implementar esto en la clase Disease
                .collect(Collectors.joining(",", "[", "]"));

        return "{"
                + "\"Employees\":" + employeesJson + ","
                + "\"Patients\":" + patientsJson + ","
                + "\"Rooms\":" + roomsJson + ","
                + "\"Queries\":" + queriesJson + ","
                + "\"Records\":" + recordsJson + ","
                + "\"Vaccines\":" + vaccinesJson + ","
                + "\"Diseases\":" + diseasesJson + ","
                + "\"Priority\":\"" + priority + "\","
                + "\"Genre\":\"" + genre + "\","
                + "\"Specialty\":\"" + specialty + "\","
                + "\"AccessType\":\"" + access + "\","
                + "\"CurrentPatient\":" + currentPatient.serializeToJson() + ","
                + "\"CurrentMedicalEmployee\":" + currentMedicalEmployee.serializeToJson()
                + "}";
    }
}
