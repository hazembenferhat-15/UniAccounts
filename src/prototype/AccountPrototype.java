package prototype;

import model.StudentAccount;

// Prototype: l'interface du prototype
public interface AccountPrototype {
    StudentAccount cloneWith(String id, String fullName, String email);
}