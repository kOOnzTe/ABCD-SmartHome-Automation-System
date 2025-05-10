package users;

public class Admin extends User {
    public Admin(String name) {
        super(name);
    }

    public void update(String message) {
        System.out.println("[Admin " + name + "] Notification: " + message);
    }

    public boolean isAdmin() { return true; }
    public boolean isRegistered() { return true; }
}