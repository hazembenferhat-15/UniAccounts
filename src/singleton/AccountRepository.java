package singleton;

import model.StudentAccount;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Singleton: un seul depot dans tout le programme
public class AccountRepository {

    private static final AccountRepository INSTANCE = new AccountRepository();

    private final Map<String, StudentAccount> accounts = new HashMap<>();

    private AccountRepository() { }

    public static AccountRepository getInstance() {
        return INSTANCE;
    }

    public void save(StudentAccount a) {
        if (accounts.containsKey(a.getId()))
            throw new IllegalStateException("id deja existant: " + a.getId());
        accounts.put(a.getId(), a);
        AuditLogger.getInstance().log("SAVE " + a.getId());
    }

    public StudentAccount findById(String id) {
        return accounts.get(id);
    }

    public List<StudentAccount> findAll() {
        return new ArrayList<>(accounts.values());
    }
}