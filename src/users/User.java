package users;

public class User {
    protected String name;
    protected boolean isChild;

    public User(String name, boolean isChild) {
        this.name = name;
        this.isChild = isChild;
    }

    public String getName() {
        return this.name;
    }

    public boolean getIsChild() {
        return this.isChild;
    }

    public void update(String message) {
        System.out.println(this.name + " (User): " + message);
    }
}