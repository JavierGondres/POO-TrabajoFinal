import backend.classes.*;
import backend.classes.Record;
import backend.controller.HospitalController;
import backend.enums.*;
import backend.file.FileHandler;

import java.io.File;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import static backend.enums.AccessType.ALTO;
import static backend.enums.Priority.ACUTE;
import static backend.enums.QueryTime.THIRTY_MINUTES;

public class Main {
    public static void main(String[] args) {
        Patient patient = new Patient("1", "John", "Doe", "1234", new Date(), 1000, new File("C:\\Users\\Scarlet\\OneDrive\\Documentos\\Java Proyects\\POO-TrabajoFinal\\AdministrativeEmployeeFile.txt"), 100, 160);
        Patient patient2 = new Patient("2", "John", "Doe", "1234", new Date(), 1000, new File("C:\\Users\\Scarlet\\OneDrive\\Documentos\\Java Proyects\\POO-TrabajoFinal\\AdministrativeEmployeeFile.txt"), 100, 160);
        MedicalEmployee doctor = new MedicalEmployee("2", "Jane", "Doe", "1234", new Date(),
                1000, new ArrayList<Specialty>(), LocalTime.of(8, 0), LocalTime.of(16, 0), new File("C:\\Users\\Scarlet\\OneDrive\\Documentos\\Java Proyects\\POO-TrabajoFinal\\AdministrativeEmployeeFile.txt"), QueryTime.NINETY_MINUTES, 200);
        Room room = new Room("1", "1", "1", true, new Date());
        Disease disease = new Disease("1", "Covid-19", true, ACUTE);
        AdministrativeEmployee admin = new AdministrativeEmployee("3", "Scarlet", "Abreu", "1234", new Date(), 1000,
                LocalTime.of(8, 0), LocalTime.of(16, 0), ALTO, new File("C:\\Users\\Scarlet\\OneDrive\\Documentos\\Java Proyects\\POO-TrabajoFinal\\AdministrativeEmployeeFile.txt"));
        Record record = new Record("1", "1", "DESCRIPCION", new ArrayList<Disease>(), new ArrayList<Disease>(),
                new ArrayList<Vaccine>(), 100, 100, new Date());
        Vaccine vaccine = new Vaccine("1", disease, 0, 100);

        HospitalController hospitalController = new HospitalController();
        hospitalController.addPatient(patient);
        hospitalController.addPatient(patient2);
        hospitalController.addEmployee(doctor);
        hospitalController.addRoom(room);
        hospitalController.addDisease(disease);
        hospitalController.addEmployee(admin);
        hospitalController.addVaccine(vaccine);
        hospitalController.addRecord(patient.getId(), record);

//        System.out.println(hospitalController.getPatients());
//        System.out.println(hospitalController.getEmployees());
//        System.out.println(hospitalController.getRooms());
//        System.out.println(hospitalController.getDiseases());
//        System.out.println(hospitalController.getVaccines());
//        System.out.println(hospitalController.getRecords());
        try {
            hospitalController.createQuery("1", patient.getId(), doctor.getId(), 100, new Date(2024, 4, 20), QueryTime.ONE_HUNDRED_TWENTY_MINUTES, LocalTime.of(10, 0), LocalTime.of(10, 50));
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println(e.getMessage());
        }

        try {
            hospitalController.createQuery("2", patient.getId(), doctor.getId(), 100, new Date(2024, 4, 20), QueryTime.THIRTY_MINUTES, LocalTime.of(10, 0), LocalTime.of(10, 30));} catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println(e.getMessage());
        }

        try {
            hospitalController.createQuery("3", patient2.getId(), doctor.getId(), 100, new Date(2024, 4, 20), QueryTime.THIRTY_MINUTES, LocalTime.of(10, 0), LocalTime.of(10, 30));
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println(e.getMessage());
        }
//        System.out.println("Money:" + admin.calculateSalary());

//        FileHandler fileHandler = new FileHandler();
//        try {
//            fileHandler.writeFile("C:\\Users\\Scarlet\\OneDrive\\Documentos\\Java Proyects\\POO-TrabajoFinal\\AdministrativeEmployeeFile.txt", "Hello World");
//            fileHandler.readFile("C:\\Users\\Scarlet\\OneDrive\\Documentos\\Java Proyects\\POO-TrabajoFinal\\AdministrativeEmployeeFile.txt");
//            fileHandler.delateFile("C:\\Users\\Scarlet\\OneDrive\\Documentos\\Java Proyects\\POO-TrabajoFinal\\AdministrativeEmployeeFile.txt");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        System.out.println(doctor.getRangeOfQueryTime());

    }
}