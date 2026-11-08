package singleton;

import java.util.ArrayList;
import java.util.List;

// Singleton: un seul journal d'audit dans tout le programme
public class AuditLogger {

    private static final AuditLogger INSTANCE = new AuditLogger();

    private final List<String> lines = new ArrayList<>();

    private AuditLogger() { }

    public static AuditLogger getInstance() {
        return INSTANCE;
    }

    public void log(String message) {
        lines.add(message);
        System.out.println("[AUDIT] " + message);
    }

    public List<String> getLines() {
        return new ArrayList<>(lines);
    }
}