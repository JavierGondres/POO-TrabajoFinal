package backend.file;

import backend.controller.HospitalController;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler implements FileOperations {

    @Override
    public void readFile(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        System.out.println(content);
    }

    @Override
    public void writeFile(String filePath, String content) throws IOException {
        Files.write(Paths.get(filePath), content.getBytes());
    }

    @Override
    public void delateFile(String filePath) throws IOException {
        Files.delete(Paths.get(filePath));
    }

    public void sendFile(String filePath, String serverAddress, int port) throws IOException {
        try (Socket socket = new Socket(serverAddress, port);
             FileInputStream fileInputStream = new FileInputStream(filePath);
             BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream())) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            out.flush();
            System.out.println("Archivo enviado al servidor.");

        } catch (UnknownHostException e) {
            System.err.println("No se pudo conectar al host " + serverAddress);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error de E/S");
            e.printStackTrace();
        }
    }
}
