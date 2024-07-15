package backend.file;

import backend.controller.HospitalController;

import java.io.IOException;

public interface FileOperations {
    void readFile(String filePath) throws IOException;
    void writeFile(String filePath, String content) throws IOException;
    void delateFile(String filePath) throws IOException;
}
