package users;

public final class Admin extends User {
    private static Admin instance;
    private Admin(String name) {
        super(name, false);
    }

    public static synchronized Admin getInstance() {
        if (instance == null) {
            instance = new Admin("admin");
        }
        return instance;
    }

	@Override
    public void update(String message) {
        System.out.println(this.name + " (Admin): " + message);
    }
}