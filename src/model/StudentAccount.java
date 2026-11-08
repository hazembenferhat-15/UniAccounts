package model;

import prototype.AccountPrototype;
import java.util.ArrayList;
import java.util.List;

// Prototype: StudentAccount est le prototype (il sait se copier)
public class StudentAccount implements AccountPrototype {

    private final String id;
    private final String fullName;
    private final String email;
    private final String phone;
    private final String level;
    private final String major;
    private final boolean scholarship;
    private final List<String> courses;

    // Builder: le constructeur est prive, on passe par le Builder
    private StudentAccount(Builder b) {
        this.id = b.id;
        this.fullName = b.fullName;
        this.email = b.email;
        this.phone = b.phone;
        this.level = b.level;
        this.major = b.major;
        this.scholarship = b.scholarship;
        this.courses = b.courses;
    }

    // Prototype: constructeur de copie (copie profonde de la liste)
    private StudentAccount(StudentAccount o, String id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = o.phone;
        this.level = o.level;
        this.major = o.major;
        this.scholarship = o.scholarship;
        this.courses = new ArrayList<>(o.courses);
    }

    // Prototype: copier le modele en changeant seulement l'identite
    @Override
    public StudentAccount cloneWith(String id, String fullName, String email) {
        check(id, fullName, email);
        return new StudentAccount(this, id, fullName, email);
    }

    // verification des champs obligatoires (utilisee par build et cloneWith)
    private static void check(String id, String fullName, String email) {
        if (id == null || id.isBlank())
            throw new IllegalArgumentException("id vide");
        if (fullName == null || fullName.isBlank())
            throw new IllegalArgumentException("nom vide");
        if (email == null || !email.contains("@"))
            throw new IllegalArgumentException("email invalide");
    }

    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getLevel() { return level; }
    public String getMajor() { return major; }
    public boolean isScholarship() { return scholarship; }
    public List<String> getCourses() { return courses; }

    @Override
    public String toString() {
        return id + " | " + fullName + " | " + level + " | " + email + " | " + courses;
    }

    // Builder: le constructeur pas a pas
    public static class Builder {
        private final String id;
        private final String fullName;
        private final String email;
        private String phone = null;
        private String level = "L1";
        private String major;
        private boolean scholarship = false;
        private List<String> courses = new ArrayList<>();

        public Builder(String id, String fullName, String email) {
            this.id = id;
            this.fullName = fullName;
            this.email = email;
        }

        public Builder phone(String phone) { this.phone = phone; return this; }
        public Builder major(String major) { this.major = major; return this; }
        public Builder scholarship(boolean s) { this.scholarship = s; return this; }
        public Builder course(String c) { this.courses.add(c); return this; }

        public Builder level(String level) {
            if (!List.of("L1", "L2", "L3", "M1", "M2").contains(level))
                throw new IllegalArgumentException("niveau invalide");
            this.level = level;
            return this;
        }

        public StudentAccount build() {
            check(id, fullName, email);
            return new StudentAccount(this);
        }
    }
}