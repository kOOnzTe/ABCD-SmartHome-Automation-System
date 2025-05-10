package users;

public final class Admin {
    private static User instance;

    private Admin() {
    }

    public static synchronized User getInstance() {
        if (instance == null) {
            System.out.println("No admin is assigned");
			return null;
        }
        return instance;
    }

	public static void setInstance(User admin) {
		if (instance.isChild) {
			System.out.println("Error: Admin cannot be a child!");
		} else {
			instance = admin;
		}
	}
}