package backend.file;

import java.io.IOException;

public class ClienteSocket {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 12345;
        String filePath = "C:\\Users\\Scarlet\\OneDrive\\Documentos\\Java Proyects\\POO-TrabajoFinal\\src\\backend\\ClienteSocketFile";

        FileHandler fileHandler = new FileHandler();

        try {
            fileHandler.sendFile(filePath, hostname, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
