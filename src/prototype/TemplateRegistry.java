package prototype;

import model.StudentAccount;
import java.util.HashMap;
import java.util.Map;

// Prototype: le registre garde un modele par promotion
public class TemplateRegistry {

    private final Map<String, StudentAccount> templates = new HashMap<>();

    public void register(String name, StudentAccount template) {
        templates.put(name, template);
    }

    public StudentAccount create(String name, String id, String fullName, String email) {
        StudentAccount t = templates.get(name);
        if (t == null)
            throw new IllegalArgumentException("modele inconnu: " + name);
        return t.cloneWith(id, fullName, email);
    }
}