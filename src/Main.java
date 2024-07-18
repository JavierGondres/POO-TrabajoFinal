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
        Patient patient = new Patient("1", "John", "Doe", "1234", new Date(), 1000, new ArrayList<String>(), new File("C:\\Users\\Scarlet\\OneDrive\\Documentos\\Java Proyects\\POO-TrabajoFinal\\AdministrativeEmployeeFile.txt"));
        MedicalEmployee doctor = new MedicalEmployee("2", "Jane", "Doe", "1234", new Date(),
                1000, new ArrayList<Specialty>(), LocalTime.of(8, 0), LocalTime.of(16, 0), new File("C:\\Users\\Scarlet\\OneDrive\\Documentos\\Java Proyects\\POO-TrabajoFinal\\AdministrativeEmployeeFile.txt"));
        Room room = new Room("1", "1","1", true, new Date());
        Disease disease = new Disease("1", "Covid-19", true, ACUTE);
        AdministrativeEmployee admin = new AdministrativeEmployee("3", "Scarlet", "Abreu", "1234", new Date(), 1000,
                LocalTime.of(8, 0), LocalTime.of(16, 0), ALTO);
        Record record = new Record("1", "DESCRIPCION", new ArrayList<Disease>(), new ArrayList<Disease>(),
                new ArrayList<Vaccine>(), 100, 100, new Date());
        Vaccine vaccine = new Vaccine("1", disease, 0, 100);

        HospitalController hospitalController = new HospitalController();
        hospitalController.addPatient(patient);
        hospitalController.addEmployee(doctor);
        hospitalController.addRoom(room);
        hospitalController.addDisease(disease);
        hospitalController.addEmployee(admin);
        hospitalController.addVaccine(vaccine);
        hospitalController.addRecord(patient.getId(), record);

        System.out.println(hospitalController.getPatients());
        System.out.println(hospitalController.getEmployees());
        System.out.println(hospitalController.getRooms());
        System.out.println(hospitalController.getDiseases());
        System.out.println(hospitalController.getVaccines());
        System.out.println(hospitalController.getRecords());
        hospitalController.registerQuery(patient.getId(), doctor.getId(), new Date(), 1000,THIRTY_MINUTES, new Date());
        System.out.println("Money:" + admin.calculateSalary());

        FileHandler fileHandler = new FileHandler();
        try {
            fileHandler.writeFile("C:\\Users\\Scarlet\\OneDrive\\Documentos\\Java Proyects\\POO-TrabajoFinal\\AdministrativeEmployeeFile.txt", "Hello World");
            fileHandler.readFile("C:\\Users\\Scarlet\\OneDrive\\Documentos\\Java Proyects\\POO-TrabajoFinal\\AdministrativeEmployeeFile.txt");
            fileHandler.delateFile("C:\\Users\\Scarlet\\OneDrive\\Documentos\\Java Proyects\\POO-TrabajoFinal\\AdministrativeEmployeeFile.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}