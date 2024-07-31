package backend.file;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 12345;
        String outputFilePath = "C:\\Users\\Scarlet\\OneDrive\\Documentos\\Java Proyects\\POO-TrabajoFinal\\src\\backend\\ClienteSocketFile";

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor escuchando en el puerto " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedInputStream in = new BufferedInputStream(clientSocket.getInputStream());
                     FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath)) {

                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = in.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, bytesRead);
                    }

                    fileOutputStream.flush();
                    System.out.println("Archivo recibido y guardado en " + outputFilePath);

                } catch (IOException e) {
                    System.err.println("Error al conectar con el cliente o al guardar el archivo");
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            System.err.println("Error de E/S en el servidor");
            e.printStackTrace();
        }
    }
}
