package users;

public class UnregisteredUser extends User {
    public UnregisteredUser(String name) {
        super(name);
    }

    public void update(String message) {
        // No notification
    }

    public boolean isAdmin() { return false; }
    public boolean isRegistered() { return false; }
}