package users;

public abstract class User {
    protected String name;

    public User(String name) {
        this.name = name;
    }

    public abstract void update(String message);

    public abstract boolean isAdmin();
    public abstract boolean isRegistered();
}