import backend.classes.*;
import backend.controller.HospitalController;
import backend.file.FileHandler;
import backend.file.Server;
import backend.enums.*;
import backend.classes.Record;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import static backend.enums.AccessType.ALTO;
import static backend.enums.Priority.ACUTE;

public class Main {
    public static void main(String[] args) {
        // Crear instancias de objetos
        Patient patient = new Patient("1", "John", "Doe", "1234", new Date(), 1000, new File("C:\\Users\\Scarlet\\OneDrive\\Documentos\\Java Proyects\\POO-TrabajoFinal\\GeneralFile.txt"), 100, 160);
        Patient patient2 = new Patient("2", "Jane", "Doe", "1234", new Date(), 1000, new File("C:\\Users\\Scarlet\\OneDrive\\Documentos\\Java Proyects\\POO-TrabajoFinal\\GeneralFile.txt"), 100, 160);
        MedicalEmployee doctor = new MedicalEmployee("2", "Jane", "Doe", "1234", new Date(),
                1000, new ArrayList<Specialty>(), LocalTime.of(8, 0), LocalTime.of(16, 0), new File("C:\\Users\\Scarlet\\OneDrive\\Documentos\\Java Proyects\\POO-TrabajoFinal\\GeneralFile.txt"), QueryTime.NINETY_MINUTES, 200);        Room room = new Room("1", "1", "1", true, new Date());
        Disease disease = new Disease("1", "Covid-19", true, ACUTE);
        AdministrativeEmployee admin = new AdministrativeEmployee("3", "Scarlet", "Abreu", "1234", new Date(), 1000, LocalTime.of(8, 0), LocalTime.of(16, 0), ALTO, new File("C:\\Users\\Scarlet\\OneDrive\\Documentos\\Java Proyects\\POO-TrabajoFinal\\GeneralFile.txt"));
        Record record = new Record("1", "Fever", "Flu-like symptoms", new ArrayList<>(), new ArrayList<>(), 100, 100, new Date());
        Vaccine vaccine = new Vaccine("1", "Flu Vaccine", "1", 0, 100);

        // Crear el controlador del hospital y agregar datos
        HospitalController hospitalController = new HospitalController();
        hospitalController.addPatient(patient);
        hospitalController.addPatient(patient2);
        hospitalController.addEmployee(doctor);
        hospitalController.addRoom(room);
        hospitalController.addDisease(disease);
        hospitalController.addEmployee(admin);
        hospitalController.addVaccine(vaccine);
        hospitalController.addRecord(patient.getId(), record);

        // Crear consultas
        try {
            hospitalController.createQuery("1", patient.getId(), doctor.getId(), 100, new Date(), QueryTime.ONE_HUNDRED_TWENTY_MINUTES, LocalTime.of(10, 0), LocalTime.of(10, 50));
            hospitalController.createQuery("2", patient.getId(), doctor.getId(), 100, new Date(), QueryTime.THIRTY_MINUTES, LocalTime.of(10, 0), LocalTime.of(10, 30));
            hospitalController.createQuery("3", patient2.getId(), doctor.getId(), 100, new Date(), QueryTime.THIRTY_MINUTES, LocalTime.of(10, 0), LocalTime.of(10, 30));
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println(e.getMessage());
        }

        // Serializar a JSON y escribir en el archivo Ya se logró
        String hospitalJson = hospitalController.serializeToJson();

        FileHandler fileHandler = new FileHandler();
        String filePath = "C:\\Users\\Scarlet\\OneDrive\\Documentos\\Java Proyects\\POO-TrabajoFinal\\GeneralFile.txt";
        try {
            fileHandler.writeFile(filePath, hospitalJson);
            String fileContent = fileHandler.readFile(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Socket
        new Thread(() -> {
            try {
                Server.main(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String hostname = "localhost";
        int port = 12345;

        try {
            fileHandler.sendFile(filePath, hostname, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
