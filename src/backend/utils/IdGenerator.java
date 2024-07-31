package backend.utils;

import java.util.UUID;

public class IdGenerator {
    public static String generarID(int i) {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().substring(0, i);
    }
}