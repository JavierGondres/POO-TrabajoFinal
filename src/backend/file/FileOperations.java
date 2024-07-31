package backend.file;

import java.io.IOException;

public interface FileOperations {
    String readFile(String filePath) throws IOException;
    void writeFile(String filePath, String content) throws IOException;
    void delateFile(String filePath) throws IOException;
}
