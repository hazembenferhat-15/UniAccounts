import model.StudentAccount;
import prototype.TemplateRegistry;
import singleton.AccountRepository;
import singleton.AuditLogger;

public class Main {
    public static void main(String[] args) {

        // 1. Builder
        StudentAccount model = new StudentAccount.Builder("TPL", "Modele", "tpl@univ.dz")
                .level("L3")
                .major("Informatique")
                .course("GL")
                .course("IHM")
                .build();

        // 2. Prototype
        TemplateRegistry reg = new TemplateRegistry();
        reg.register("L3-INFO", model);

        StudentAccount a1 = reg.create("L3-INFO", "2024001", "Ahmed Ali", "ahmed@univ.dz");
        StudentAccount a2 = reg.create("L3-INFO", "2024002", "Sara Ben", "sara@univ.dz");

        // preuve de la copie profonde
        a1.getCourses().add("SE2");
        System.out.println("a1 = " + a1);
        System.out.println("a2 = " + a2);

        // 3. Singleton
        AccountRepository repo = AccountRepository.getInstance();
        repo.save(a1);
        repo.save(a2);

        System.out.println("Meme depot ? " + (repo == AccountRepository.getInstance()));
        System.out.println("Meme logger ? " + (AuditLogger.getInstance() == AuditLogger.getInstance()));

        // 4. Erreurs attendues
        try {
            repo.save(a1);
        } catch (IllegalStateException e) {
            System.out.println("Doublon refuse: " + e.getMessage());
        }
        try {
            new StudentAccount.Builder("1", "X", "sans-arobase").build();
        } catch (IllegalArgumentException e) {
            System.out.println("Email refuse: " + e.getMessage());
        }
    }
}