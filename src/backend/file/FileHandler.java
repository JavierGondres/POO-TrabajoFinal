package backend.file;

import backend.controller.HospitalController;
import java.io.*;
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
}
