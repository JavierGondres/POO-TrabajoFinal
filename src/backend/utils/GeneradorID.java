package backend.utils;

import java.util.UUID;

public class GeneradorID {
    public static String generarID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().substring(0, 8);
    }
}