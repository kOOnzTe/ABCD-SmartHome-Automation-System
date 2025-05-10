package users;

public abstract class User {
    protected String name;

    public User(String name) {
        this.name = name;
    }

    public abstract void update(String message);

}