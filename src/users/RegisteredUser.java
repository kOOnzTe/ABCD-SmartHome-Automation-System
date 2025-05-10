package users;

public class RegisteredUser extends User {
    public RegisteredUser(String name) {
        super(name);
    }

    public void update(String message) {
        System.out.println("[User " + name + "] Notification: " + message);
    }

    public boolean isAdmin() { return false; }
    public boolean isRegistered() { return true; }
}